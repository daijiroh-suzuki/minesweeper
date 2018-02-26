package jp.dsuzuki.minesweeper.main.parts.boad;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

public class Counter extends JLabel {

   /** コンポーネントの幅 */
    private static final int SIZE_X = 60;
    /** コンポーネントの高さ */
    private static final int SIZE_Y = 30;

    /** カウント */
    private int count;

    /**
     * コンストラクタ
     */
    public Counter(int cnt) {

        // 推奨サイズの設定
        setPreferredSize(new Dimension(SIZE_X, SIZE_Y));
        // 背景色を設定
        setBackground(Color.BLACK);
        // 背景色を不透明に変更
        setOpaque(true);
        // 前景色(文字色)を変更
        setForeground(Color.RED);
        // 文字の水平方向表示位置を変更
        setHorizontalAlignment(JLabel.CENTER);

        // カウンタの初期化
        init(cnt);
    }

    /**
     * カウンタの初期化
     *
     * @param cnt
     */
    public void init(int cnt) {

        count = cnt;
        setText(String.format("%03d", count));
    }
}
