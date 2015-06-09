package jp.dsuzuki.minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import jp.dsuzuki.minesweeper.common.CommonConstant;
import jp.dsuzuki.minesweeper.main.parts.boad.MainBoad;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/** パネルサイズ：幅 */
	private static final int WIDTH = CommonConstant.TILE_X * CommonConstant.TILE_SIZE + 20;
	/** パネルサイズ：高さ */
	private static final int HEIGHT = CommonConstant.TILE_Y * CommonConstant.TILE_SIZE + 60;
	
	/** 盤面 */
	private MainBoad boad;
	
	/**
	 * コンストラクタ
	 */
	public MainPanel() {
		
		// パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// ボタンを生成
		JButton button = new JButton("(・＿・)");
		// 盤面クラスを生成
		boad = new MainBoad(button);
		
		// ボタンのアクションリスナーを設定
		button.addMouseListener(
				new MouseAdapter() {
					public void mouseReleased(MouseEvent e) {
						JButton btn = (JButton)e.getSource();
						btn.setText("(・＿・)");
						System.out.println("ボタンが押されました。盤面を初期化します。");
						boad.init();
					}
				});
		
		add(button, BorderLayout.NORTH);
		add(boad, BorderLayout.CENTER);		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		
	}	
}
