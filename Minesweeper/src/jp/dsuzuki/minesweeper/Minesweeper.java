package jp.dsuzuki.minesweeper;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import jp.dsuzuki.minesweeper.common.CommonConstant;
import jp.dsuzuki.minesweeper.common.Difficulty;

/**
 * マインスイーパーのフレームクラス
 * @author daijiroh
 *
 */
public class Minesweeper extends JFrame implements ActionListener {

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
        JMenu mnFile = new JMenu("ファイル");
        JMenu mnHelp = new JMenu("ヘルプ");

        // メニューアイテムを生成
        JMenuItem mniNew     = new JMenuItem("新規");
        JMenuItem mniExit    = new JMenuItem("終了");
        JMenuItem mniVersion = new JMenuItem("バージョン情報");

        JRadioButtonMenuItem mniDifficulty1 = new JRadioButtonMenuItem("初級");
        JRadioButtonMenuItem mniDifficulty2 = new JRadioButtonMenuItem("中級");
        JRadioButtonMenuItem mniDifficulty3 = new JRadioButtonMenuItem("上級");
        mniDifficulty1.setSelected(true);

        ButtonGroup group = new ButtonGroup();
        group.add(mniDifficulty1);
        group.add(mniDifficulty2);
        group.add(mniDifficulty3);

        mniNew.addActionListener(this);
        mniExit.addActionListener(this);

        mniDifficulty1.addActionListener(this);
        mniDifficulty2.addActionListener(this);
        mniDifficulty3.addActionListener(this);

        // メニューにメニューアイテムを追加
        mnFile.add(mniNew);
        mnFile.addSeparator();
        mnFile.add(mniDifficulty1);
        mnFile.add(mniDifficulty2);
        mnFile.add(mniDifficulty3);
        mnFile.addSeparator();
        mnFile.add(mniExit);
        mnHelp.add(mniVersion);

        // メニューバーにメニューを追加
        menubar.add(mnFile);
        menubar.add(mnHelp);

        // メニューバーをフレームに追加
        setJMenuBar(menubar);

        // メインパネルをフレームに追加
        setMainPanel(Difficulty.BEGINNER);
    }

    /**
     * メインパネルをフレームに追加
     *
     * @param difficulty 難易度
     */
    private void setMainPanel(Difficulty difficulty) {

        // メインパネルを生成
        MainPanel panel = new MainPanel(difficulty);

        // コンテントペインを取得
        Container contentPane = getContentPane();
        // コンテントペイン上のコンポーネントを削除
        contentPane.removeAll();
        // コンテントペインにメインパネルを追加
        contentPane.add(panel, BorderLayout.CENTER);

        // パネルサイズに合わせてフレームサイズを自動設定
        pack();
    }

    /**
     * メニューイベント処理
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();

        if("新規".equals(command)) {

        } else if("初級".equals(command)) {
            setMainPanel(Difficulty.BEGINNER);
        } else if("中級".equals(command)) {
            setMainPanel(Difficulty.MIDDLE);
        } else if("上級".equals(command)) {
            setMainPanel(Difficulty.ADVANCED);
        } else if("終了".equals(command)) {
            System.exit(0);
        }
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
