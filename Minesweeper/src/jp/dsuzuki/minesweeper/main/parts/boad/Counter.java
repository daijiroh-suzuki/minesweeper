package jp.dsuzuki.minesweeper.main.parts.boad;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;

import jp.dsuzuki.minesweeper.common.Difficulty;

public class Counter extends JLabel {

   /** コンポーネントの幅 */
    private static final int SIZE_X = 60;
    /** コンポーネントの高さ */
    private static final int SIZE_Y = 30;

    /** 難易度 */
    private Difficulty difficulty;

    /** カウント */
    private int count;

    /**
     * コンストラクタ
     */
    public Counter(Difficulty dif) {

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

        // 難易度を設定
        difficulty = dif;

        // カウンタの初期化
        init();
    }

    /**
     * カウンタの初期化
     *
     * @param cnt
     */
    public void init() {

        count = difficulty.BOMB_NUM;
        repaint();
    }

    /**
     * カウント加算
     */
    public void countUp() {
        count++;
        repaint();
    }

    /**
     * カウント減算
     */
    public void countDown() {
        count--;
        repaint();
    }

    /**
     * 描画処理
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(count < 0) {
            setText(String.format("+%03d", count));
        } else {
            setText(String.format("%03d", count));
        }
    }
}
