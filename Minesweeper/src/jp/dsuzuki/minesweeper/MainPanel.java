package jp.dsuzuki.minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import jp.dsuzuki.minesweeper.common.Difficulty;
import jp.dsuzuki.minesweeper.main.parts.boad.Counter;
import jp.dsuzuki.minesweeper.main.parts.boad.InitButton;
import jp.dsuzuki.minesweeper.main.parts.boad.MainBoad;
import jp.dsuzuki.minesweeper.main.parts.boad.Timer;

public class MainPanel extends JPanel {

    /** 初期化ボタン */
    private static InitButton button;
    /** タイマー */
    private static Timer timer;
    /** カウンタ */
    private static Counter counter;
    /** 盤面 */
    private static MainBoad boad;

    /**
     * コンストラクタ
     */
    public MainPanel(Difficulty difficulty) {

        // コンポーネントの幅を取得
        int width = difficulty.TILE_X * difficulty.TILE_SIZE  + 20;
        // コンポーネントの高さを取得
        int height = difficulty.TILE_Y * difficulty.TILE_SIZE + 90;

        // コンポーネントの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(width, height));

        // 初期化ボタンを生成
        button = new InitButton();
        // タイマーを生成
        timer = new Timer();
        // カウンタを生成
        counter = new Counter(difficulty);
        // 盤面クラスを生成
        boad = new MainBoad(difficulty);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        panel.add(timer);
        panel.add(button);
        panel.add(counter);

        add(panel, BorderLayout.NORTH);
        add(boad, BorderLayout.CENTER);
    }

    /**
     * 初期化ボタンを取得
     *
     * @return
     */
    public static InitButton getButton() {
        return button;
    }

    /**
     * タイマーを取得
     *
     * @return
     */
    public static Timer getTimer() {
        return timer;
    }

    /**
     * カウンタを取得
     *
     * @return
     */
    public static Counter getCounter() {
        return counter;
    }

    /**
     * 盤面を取得
     *
     * @return
     */
    public static MainBoad getBoard() {
        return boad;
    }
}
