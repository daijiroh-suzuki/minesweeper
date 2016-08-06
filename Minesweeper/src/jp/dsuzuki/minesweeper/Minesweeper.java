package jp.dsuzuki.minesweeper;

import java.awt.Container;

import javax.swing.JFrame;

import jp.dsuzuki.minesweeper.common.CommonConstant;

/**
 * マインスイーパーのフレームクラス
 * @author daijiroh
 *
 */
public class Minesweeper extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ
	 */
	public Minesweeper() {
		
		// タイトルを設定する
		setTitle(CommonConstant.FRAME_TITLE);
		
		// メインパネルを生成してフレームに追加
		MainPanel panel = new MainPanel(CommonConstant.MIDDLE_INDEX);
		Container contentPane = getContentPane();
		contentPane.add(panel);
		
		// パネルサイズに合わせてフレームサイズを自動設定
		pack();
	}
	
	/**
	 * エントリポイント
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		
		// フレームを生成
		Minesweeper frame = new Minesweeper();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// フレームを画面の中央に表示
		frame.setLocationRelativeTo(null);
		
		// フレームサイズを変更不可にする
		frame.setResizable(false);
		
		// フレームを表示する
		frame.setVisible(true);
	}
}
