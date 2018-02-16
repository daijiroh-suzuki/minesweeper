package jp.dsuzuki.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import jp.dsuzuki.minesweeper.common.CommonConstant;
import jp.dsuzuki.minesweeper.common.Difficulty;
import jp.dsuzuki.minesweeper.main.parts.boad.MainBoad;

public class MainPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    /** 盤面 */
    private MainBoad boad;

    /**
     * コンストラクタ
     */
    public MainPanel(Difficulty difficulty) {

        // パネルの幅を取得
        int width = difficulty.TILE_X * difficulty.TILE_SIZE + 20;
        // パネルの高さを取得
        int height = difficulty.TILE_Y * difficulty.TILE_SIZE + 60;

        // パネルの推奨サイズを設定、pack()するときに必要
        setPreferredSize(new Dimension(width, height));

        // ボタンを生成
        JButton button = new JButton(CommonConstant.BUTTON_INIT);
        // 盤面クラスを生成
        boad = new MainBoad(button, difficulty);

        // ボタンのアクションリスナーを設定
        button.addMouseListener(
                new MouseAdapter() {
                    public void mouseReleased(MouseEvent e) {
                        JButton btn = (JButton)e.getSource();
                        btn.setText(CommonConstant.BUTTON_INIT);
                        System.out.println("ボタンが押されました。盤面を初期化します。");
                        boad.init();
                    }
                });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        // タイマー用ラベル作成
        JLabel label1 = new JLabel("00:00");
        label1.setPreferredSize(new Dimension(60,30));
        label1.setBorder(new LineBorder(Color.BLACK, 1, false));
        label1.setBackground(Color.BLACK);              // 背景色を黒に変更
        label1.setOpaque(true);                         // 背景色を不透明に変更
        label1.setHorizontalAlignment(JLabel.CENTER);   // 文字表示位置を中心に変更
        label1.setForeground(Color.RED);                // 前景色(文字色)を変更

        // 爆弾の残数カウント用ラベル作成
        JLabel label2 = new JLabel("000");
        label2.setPreferredSize(new Dimension(60,30));
        label2.setBorder(new LineBorder(Color.BLACK, 1, false));
        label2.setBackground(Color.BLACK);              // 背景色を黒に変更
        label2.setOpaque(true);                         // 背景色を不透明に変更
        label2.setHorizontalAlignment(JLabel.CENTER);   // 文字表示位置を中心に変更
        label2.setForeground(Color.RED);                // 前景色(文字色)を変更

        panel.add(label1);
        panel.add(button);
        panel.add(label2);

        add(panel, BorderLayout.NORTH);
        add(boad, BorderLayout.CENTER);
    }

    /**
     * 描画処理
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
