/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.afw.biz.matlab;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jp.afw.core.util.PathUtility;
import jp.afw.core.util.StringUtility;

/**
 * このクラスは、MATLABのスクリプトを実行するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2013/06/26
 * @author Kawakicchi
 */
public class MatlabExecuter {

	/**
	 * このEnumは、MATLABのからの実行結果を表現するEnumです。
	 * 
	 * @since 1.0.0
	 * @version 1.0.0 2013/07/02
	 * @author Kawakicchi
	 * 
	 */
	public static enum Result {
		/**
		 * 実行結果 - 成功
		 */
		success(1),
		/**
		 * 実行結果 - キャンセル
		 */
		cancel(2),
		/**
		 * 実行結果 - 失敗
		 */
		error(3);

		/**
		 * タイプ
		 */
		private int type;

		/**
		 * コンストラクタ
		 * 
		 * @param aType タイプ
		 */
		private Result(final int aType) {
			type = aType;
		}

		/**
		 * タイプを取得する。
		 * 
		 * @return タイプ
		 */
		public int getType() {
			return type;
		}
	}

	/**
	 * ログファイル
	 */
	private String logFile;

	/**
	 * 実行スクリプトファイル
	 */
	private String runScriptFileName = "run.m";

	/**
	 * 成功判定ファイル
	 */
	private String successFileName = "success";

	/**
	 * 失敗判定ファイル
	 */
	private String errorFileName = "error";

	/**
	 * 改行コード
	 */
	private String lineSeparator = "\n";

	/**
	 * コンストラクタ
	 */
	public MatlabExecuter() {
		try {
			lineSeparator = System.getProperty("line.separator");
		} catch (SecurityException e) {
		}
	}

	/**
	 * ログファイルパスを設定する。
	 * 
	 * @param aPath ログファイルパス
	 */
	public void setLogFile(final String aPath) {
		logFile = aPath;
	}

	/**
	 * 実行スクリプトファイル名を設定する。
	 * 
	 * @param aName ファイル名
	 */
	public void setRunScriptFileName(final String aName) {
		runScriptFileName = aName;
	}

	/**
	 * 成功判定ファイル名を設定する。
	 * 
	 * @param aName ファイル名
	 */
	public void setSuccessFileName(final String aName) {
		successFileName = aName;
	}

	/**
	 * 失敗判定ファイル名を設定する。
	 * 
	 * @param aName ファイル名
	 */
	public void setErrorFileName(final String aName) {
		errorFileName = aName;
	}

	/**
	 * MATLABスクリプトファイルを実行する。
	 * 
	 * @param aTargetDir 実行ディレクトリ
	 * @param aMfileName MATLABスクリプトファイル名
	 * @return 実行結果
	 * @throws InterruptedException {@link InterruptedException}
	 * @throws IOException {@link IOException}
	 */
	public Result execute(final String aTargetDir, final String aMfileName) throws InterruptedException, IOException {
		Result result = Result.cancel;

		String runFilePath = createRunScriptFile(aTargetDir, aMfileName);

		List<String> list = new ArrayList<String>();
		list.add("matlab");
		list.add("-nosplash");
		list.add("-minimize");
		list.add("-wait");
		list.add("-r");
		list.add("run('" + runFilePath + "');");
		if (StringUtility.isNotEmpty(logFile)) {
			list.add("-logfile");
			list.add(logFile);
		}

		ProcessBuilder pb = new ProcessBuilder(list);
		Process p = pb.start();
		p.waitFor();

		File successFile = new File(PathUtility.cat(aTargetDir, successFileName));
		if (successFile.isFile()) {
			result = Result.success;
		} else {
			File errorFile = new File(PathUtility.cat(aTargetDir, errorFileName));
			if (errorFile.isFile()) {
				result = Result.error;
			}
		}
		return result;
	}

	/**
	 * スクリプトファイル実行用スクリプトファイルを生成する。
	 * <p>
	 * </p>
	 * 
	 * @param aTargetDir 対象ディレクトリ
	 * @param aScriptName 実行スクリプトファイル
	 * @return 実行用スクリプトファイルパス。
	 * @throws IOException IO操作時に問題が発生した場合。
	 */
	private String createRunScriptFile(final String aTargetDir, final String aScriptName) throws IOException {
		String runFilePath = PathUtility.cat(aTargetDir, runScriptFileName);
		String scriptName = aScriptName;
		int index = aScriptName.lastIndexOf(".");
		if (-1 != index) {
			scriptName = aScriptName.substring(0, index);
		}

		StringBuffer sb = new StringBuffer();
		sb.append("try").append(lineSeparator);
		sb.append("cd('").append(aTargetDir).append("');").append(lineSeparator);
		sb.append(scriptName).append(lineSeparator);
		sb.append("successFileId = fopen('").append(successFileName).append("','W');").append(lineSeparator);
		sb.append("fclose(successFileId);").append(lineSeparator);
		sb.append("catch ME").append(lineSeparator);
		sb.append("error=ME.message").append(lineSeparator);
		sb.append("errorFileId = fopen('").append(errorFileName).append("','W');").append(lineSeparator);
		sb.append("fclose(errorFileId);").append(lineSeparator);
		sb.append("end").append(lineSeparator);
		sb.append("exit;");

		BufferedWriter writer = new BufferedWriter(new FileWriter(runFilePath));
		writer.write(sb.toString());
		writer.close();
		return runFilePath;
	}
}
