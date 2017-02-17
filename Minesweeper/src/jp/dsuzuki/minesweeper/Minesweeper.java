package jp.dsuzuki.minesweeper;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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

		// メニューバーを生成
		JMenuBar menubar = new JMenuBar();

		// メニューを生成
		JMenu menu1 = new JMenu("ファイル");
		JMenu menu2 = new JMenu("編集");

		// メニューアイテムを生成
		JMenuItem menuitem1 = new JMenuItem("新規");
		JMenuItem menuitem2 = new JMenuItem("終了");

		// メニューにメニューアイテムを追加
		menu1.add(menuitem1);
		menu1.add(menuitem2);

		// メニューバーにメニューを追加
		menubar.add(menu1);
		menubar.add(menu2);

		// メニューバーをフレームに追加
		setJMenuBar(menubar);

		// メインパネルを生成してフレームに追加
		MainPanel panel = new MainPanel(CommonConstant.BEGINNER_INDEX);
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
