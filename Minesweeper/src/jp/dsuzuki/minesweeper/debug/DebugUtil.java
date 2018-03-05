package jp.dsuzuki.minesweeper.debug;

import jp.dsuzuki.minesweeper.common.CommonConstant;
import jp.dsuzuki.minesweeper.common.ConfigReader;

/**
 * デバッグ用ユーティリティクラス
 *
 * @author daijiroh
 *
 */
public class DebugUtil {

    /** デバッグモード */
    private static int mode;

    static {
        // デバッグモード取得
        String tmp = ConfigReader.getProperty(CommonConstant.KEY_DEBUGMODE);
        if(tmp != null) {
            mode = Integer.parseInt(tmp);
        } else {
            mode = 0;
        }
    }

    /**
     * 標準出力にログを出力
     *
     * @param str 出力するログメッセージ
     */
    public static void println(String str) {
        if(mode == 0) {
            return;
        }
        System.out.println(str);
    }

    /**
     * 標準出力にログを出力
     */
    public static void println() {
        if(mode == 0) {
            return;
        }
        System.out.println();
    }

    /**
     * 標準出力に二次元配列設定値を出力
     *
     * @param array
     */
    public static void printArray(int[][] array) {
        if(mode == 0) {
            return;
        }

        for(int i=0; i<array.length; i++) {
            for(int j=0; j<array[i].length; j++) {
                System.out.printf("%2d ", array[i][j]);
            }
            System.out.println();
        }

    }
}
