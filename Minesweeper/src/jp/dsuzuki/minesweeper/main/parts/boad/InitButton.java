package jp.dsuzuki.minesweeper.main.parts.boad;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import jp.dsuzuki.minesweeper.MainPanel;
import jp.dsuzuki.minesweeper.debug.DebugUtil;

public class InitButton extends JButton implements MouseListener {

    /** ボタンラベル：初期値 */
    public static final String BUTTON_INIT = "(・＿・)";
    /** ボタンラベル：ゲームオーバー */
    public static final String BUTTON_GAME_OVER = "(´･ω･`) ｼｮﾎﾞｰﾝ";
    /** ボタンラベル：ゲームクリア */
    public static final String BUTTON_GAME_CLEAR = "(`･ω･´)ｼｬｷｰﾝ";

    /**
     * コンストラクタ
     */
    public InitButton() {

        // ラベルを設定
        setText(BUTTON_INIT);

        // MouseListner設定
        addMouseListener(this);
    }

    // マウスボタンが押された時
    @Override
    public void mousePressed(MouseEvent e) {}

    // マウスボタンが離された時
    @Override
    public void mouseReleased(MouseEvent e) {
        DebugUtil.println("ボタンが押されました。盤面を初期化します。");
        // ラベルを初期化
        setText(BUTTON_INIT);
        // 盤面を初期化
        MainPanel.getBoard().init();
        // タイマーを初期化
        MainPanel.getTimer().init();
        // カウンタを初期化
        MainPanel.getCounter().init();
    }

    // マウスボタンがクリックされた時
    @Override
    public void mouseClicked(MouseEvent e) {}
    // マウスポインタがコンポーネント領域に入った時
    @Override
    public void mouseEntered(MouseEvent e) {}
    // マウスポインタがコンポーネント領域から出た時
    @Override
    public void mouseExited(MouseEvent e) {}
}
