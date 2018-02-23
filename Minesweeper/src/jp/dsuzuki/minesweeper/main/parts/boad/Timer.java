package jp.dsuzuki.minesweeper.main.parts.boad;

import java.awt.Color;
import java.awt.Dimension;
import java.text.SimpleDateFormat;

import javax.swing.JLabel;

public class Timer extends JLabel implements Runnable {

    /** コンポーネントの幅 */
    private static final int SIZE_X = 60;
    /** コンポーネントの高さ */
    private static final int SIZE_Y = 30;

    /** 初期表示文字列 */
    private static final String INIT_TEXT = "00:00";
    /** 表示フォーマット */
    private static final String TEXT_FORMAT = "mm:ss";

    /** 開始時間(ナノ秒) */
    private long startTime = 0L;
    /** 経過時間(ナノ秒) */
    private long elapsedTime = 0L;

    /** 表示フォーマット */
    private SimpleDateFormat formatter;

    /** タイマースレッド */
    private Thread timerThread;

    /** 実行フラグ */
    private boolean running;

    /**
     * コンストラクタ
     */
    public Timer() {

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

        // 表示フォーマットを設定
        formatter = new SimpleDateFormat(TEXT_FORMAT);

        // タイマー初期化
        init();
    }

    /**
     * タイマー初期化
     */
    public void init() {

        // 開始時間を初期化
        startTime = 0L;
        // 経過時間を初期化
        elapsedTime = 0L;
        // 表示文字列を初期化
        setText(INIT_TEXT);

        // 実行中の場合、停止する
        if (running) {
            running = false;
        }
    }

    /**
     * タイマー開始
     */
    public void start() {

        // 開始時間を取得
        startTime = System.nanoTime();

        // タイマースレッドを開始
        timerThread = new Thread(this);
        timerThread.start();
    }

    /**
     * タイマー停止
     */
    public void stop() {
        running = false;
    }

    /**
     * タイマースレッド
     */
    @Override
    public void run() {

        running = true;

        while (running) {
            // 経過時間を取得
            elapsedTime = System.nanoTime() - startTime;
            // 表示文字列を取得
            String timeFormatted = formatter.format(elapsedTime / 1000000L); // ナノ秒 -> ミリ秒
            // 表示文字列を設定
            setText(timeFormatted);
            repaint();

            // 休止
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
