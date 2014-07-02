package org.azkfw.biz.archive;

import java.io.File;
import java.nio.charset.Charset;

/**
 * このクラスは、ZIPアーカイブ機能を実装するクラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/06/30
 * @author Kawakicchi
 */
public class ZipArchiver implements Archiver {

	/** 文字コード */
	private Charset charset;

	/**
	 * コンストラクタ
	 */
	public ZipArchiver() {
		charset = Charset.forName(System.getProperty("file.encoding"));
	}

	/**
	 * コンストラクタ
	 * 
	 * @param aCharset 文字コード
	 */
	public ZipArchiver(final Charset aCharset) {
		charset = aCharset;
	}

	@Override
	public boolean compression(final File aArchiveFile, final File aSourceFile) {
		return false;
	}

	@Override
	public boolean decompression(final File aArchiveFile, final File aDistDirectory) {
		return false;
	}

}
