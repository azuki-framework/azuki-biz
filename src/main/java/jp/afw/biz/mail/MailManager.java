package jp.afw.biz.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.afw.business.BusinessServiceException;
import jp.afw.business.manager.AbstractManager;
import jp.afw.core.util.StringUtility;
import jp.afw.persistence.context.Context;

import org.apache.commons.digester3.Digester;
import org.xml.sax.SAXException;

/**
 * このクラスは、メールの管理を行うマネージャークラスです。
 * 
 * @since 1.0.0
 * @version 1.0.0 2014/01/28
 * @author Kawakicchi
 */
public class MailManager extends AbstractManager {

	/**
	 * Instance
	 */
	private static final MailManager INSTANCE = new MailManager();

	/**
	 * mails
	 */
	private Map<String, Map<String, MailData>> mails = new HashMap<String, Map<String, MailData>>();

	/**
	 * コンストラクタ
	 * <p>
	 * インスタンス生成を禁止する。
	 * </p>
	 */
	private MailManager() {
		super(MailManager.class);
	}

	/**
	 * 初期か処理を行う。
	 */
	public synchronized static void initialize() {
		INSTANCE.doInitialize();
	}

	/**
	 * 解放処理を行う。
	 */
	public synchronized static void destroy() {
		INSTANCE.doDestroy();
	}

	/**
	 * メール情報をロードする。
	 * 
	 * @param aStream メール情報
	 * @param aContext コンテキスト
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	public synchronized static void load(final InputStream aStream, final Context aContext) throws BusinessServiceException, IOException {
		INSTANCE.doLoad(StringUtility.EMPTY, aStream, aContext);
	}

	/**
	 * 初期化処理を行う。
	 */
	private void doInitialize() {

	}

	/**
	 * 解放処理を行う。
	 */
	private void doDestroy() {

	}

	/**
	 * メール情報をロードする。
	 * 
	 * @param aNamespace 名前空間
	 * @param aStream ロジック情報
	 * @param aContext コンテキスト
	 * @throws BusinessServiceException ビジネスサービスに起因する問題が発生した場合
	 * @throws IOException 入出力操作に起因する問題が発生した場合
	 */
	private void doLoad(final String aNamespace, final InputStream aStream, final Context aContext) throws BusinessServiceException, IOException {

		String crlf = "\r\n";
		try {
			crlf = System.getProperty("line.separator");
		} catch (SecurityException e) {
		}

		List<MailEntity> mailList = null;
		try {
			Digester digester = new Digester();
			digester.addObjectCreate("azuki/mails", ArrayList.class);
			digester.addObjectCreate("azuki/mails/mail", MailEntity.class);
			digester.addSetProperties("azuki/mails/mail");
			digester.addSetNext("azuki/mails/mail", "add");
			mailList = digester.parse(aStream);
		} catch (SAXException ex) {
			error(ex);
			throw new IOException(ex);
		} catch (IOException ex) {
			error(ex);
			throw new IOException(ex);
		}

		Map<String, MailData> m = null;
		if (mails.containsKey(aNamespace)) {
			m = mails.get(aNamespace);
		} else {
			m = new HashMap<String, MailData>();
		}

		for (int i = 0; i < mailList.size(); i++) {
			MailEntity mail = mailList.get(i);
			if (m.containsKey(mail.getName())) {
				throw new BusinessServiceException("Duplicate mail name.[" + mail.getName() + "]");
			} else {
				BufferedReader reader = null;
				try {
					MailData data = new MailData();

					data.setName(mail.getName());
					StringBuffer sb = new StringBuffer();
					InputStream is = aContext.getResourceAsStream(mail.getTemplate());
					if (null != is) {
						reader = new BufferedReader(new InputStreamReader(is));
						String str = null;
						while (null != (str = reader.readLine())) {
							if (0 < sb.length()) {
								sb.append(crlf);
							}
							sb.append(str);
						}
					}
					data.setTemplate(sb.toString());
					m.put(mail.getName(), data);
				} finally {
					if (null != reader) {
						try {
							reader.close();
						} catch (IOException ex) {

						}
					}
				}
			}
		}
		mails.put(aNamespace, m);
	}

	public static class MailData {
		private String name;

		private String template;

		public void setName(final String aName) {
			name = aName;
		}

		public String getName() {
			return name;
		}

		public void setTemplate(final String aTemplate) {
			template = aTemplate;
		}

		public String getTemplate() {
			return template;
		}
	}

	/**
	 * このクラスは、メール情報を保持するエンティティクラスです。
	 * 
	 * @since 1.0.0
	 * @version 1.0.0 12/09/21
	 * @author Kawakicchi
	 * 
	 */
	public static class MailEntity {

		private String name;

		private String template;

		public void setName(final String aName) {
			name = aName;
		}

		public String getName() {
			return name;
		}

		public void setTemplate(final String aTemplate) {
			template = aTemplate;
		}

		public String getTemplate() {
			return template;
		}
	}
}
