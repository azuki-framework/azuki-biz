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
package org.azkfw.biz.archive;

import java.io.File;

/**
 * このインターフェースは、アーカイブ機能を表現するインターフェースです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/06/30
 * @author Kawakicchi
 */
public interface Archiver {

	/**
	 * アーカイブファイルを作成する。
	 * 
	 * @param aArchiveFile アーカイブファイル
	 * @param aSourceFile ソースファイル
	 * @return
	 */
	public void compression(final File aArchiveFile, final File aSourceFile) throws ArchiveException;

	/**
	 * アーカイブファイルを展開する。
	 * 
	 * @param aArchiveFile アーカイブファイル
	 * @param aDistDirectory 展開ディレクトリ
	 * @return
	 */
	public void decompression(final File aArchiveFile, final File aDistDirectory) throws ArchiveException;

}
