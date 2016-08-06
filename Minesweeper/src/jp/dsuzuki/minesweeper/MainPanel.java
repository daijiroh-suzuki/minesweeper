package jp.dsuzuki.minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import jp.dsuzuki.minesweeper.common.CommonConstant;
import jp.dsuzuki.minesweeper.main.parts.boad.MainBoad;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/** 盤面 */
	private MainBoad boad;
	
	/**
	 * コンストラクタ
	 */
	public MainPanel(int index) {
		
		Map<String, Integer> settingMap = CommonConstant.SETTING_LIST.get(index);
		// パネルの幅を取得
		int width = settingMap.get(CommonConstant.TILE_X) * settingMap.get(CommonConstant.TILE_SIZE) + 20;
		// パネルの高さを取得
		int height = settingMap.get(CommonConstant.TILE_Y) * settingMap.get(CommonConstant.TILE_SIZE) + 60;
		
		// パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(width, height));
		
		// ボタンを生成
		JButton button = new JButton(CommonConstant.BUTTON_INIT);
		// 盤面クラスを生成
		boad = new MainBoad(button, index);
		
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
		
		add(button, BorderLayout.NORTH);
		add(boad, BorderLayout.CENTER);		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);		
	}	
}
