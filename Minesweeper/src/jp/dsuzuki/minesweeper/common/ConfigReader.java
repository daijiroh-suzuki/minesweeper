package jp.dsuzuki.minesweeper.common;

import java.io.InputStream;
import java.util.Properties;

import jp.dsuzuki.minesweeper.debug.DebugUtil;

/**
 * 設定ファイル読み込みクラス (Singleton)
 *
 * @author daijiroh
 *
 */
public class ConfigReader {

    /** バージョン設定ファイル */
    private static final String SYSTEM_FILE = "/system.properties";
    /** メッセージ設定ファイル */
    private static final String MESSAGE_FILE = "/message.properties";

    private static ConfigReader instance = new ConfigReader();

    /** Properties */
    private static Properties properties;

    /**
     * コンストラクタ
     */
    private ConfigReader() {

        properties = new Properties();
        try {
            // バージョン設定ファイル読み込み
            InputStream systemIs = this.getClass().getResourceAsStream(SYSTEM_FILE);
            properties.load(systemIs);
            systemIs.close();

            // メッセージ設定ファイル読み込み
            InputStream messageIs = this.getClass().getResourceAsStream(MESSAGE_FILE);
            properties.load(messageIs);
            messageIs.close();

        } catch (Exception e) {
            DebugUtil.println(e.getMessage());
        }
    }

    /**
     * キーに対応する値を取得
     *
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * インスタンスを取得
     *
     * @return
     */
    public static ConfigReader getInstance() {
        return instance;
    }
}
