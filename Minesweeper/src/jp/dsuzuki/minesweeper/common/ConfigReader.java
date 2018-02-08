package jp.dsuzuki.minesweeper.common;

import java.io.InputStream;
import java.util.Properties;

/**
 * 設定ファイル読み込みクラス (Singleton)
 *
 * @author daijiroh
 *
 */
public class ConfigReader {

    /** バージョン設定ファイル */
    private static final String VERSION_FILE = "/version.properties";

    private static ConfigReader instance = new ConfigReader();

    /** Properties */
    private Properties properties;

    /**
     * コンストラクタ
     */
    private ConfigReader() {

        properties = new Properties();
        try {
            InputStream inputStream = this.getClass().getResourceAsStream(VERSION_FILE);
            properties.load(inputStream);
            inputStream.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * キーに対応する値を取得
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
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
