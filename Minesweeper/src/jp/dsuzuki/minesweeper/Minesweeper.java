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
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import jp.dsuzuki.minesweeper.common.CommonConstant;
import jp.dsuzuki.minesweeper.common.ConfigReader;
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

        // メニューバーをフレームに追加
        setMenuBar();

        // メインパネルをフレームに追加
        setMainPanel(Difficulty.BEGINNER);
    }

    /**
     * メニューバーをフレームに追加
     */
    private void setMenuBar() {

        // メニューバーを生成
        JMenuBar menubar = new JMenuBar();

        // メニューを生成
        JMenu mnFile = new JMenu(CommonConstant.MENU_FILE);
        JMenu mnHelp = new JMenu(CommonConstant.MENU_HELP);

        // メニューアイテムを生成
        JMenuItem mniNew = new JMenuItem(CommonConstant.MENUITEM_NEW);
        JMenuItem mniExit = new JMenuItem(CommonConstant.MENUITEM_EXIT);
        JMenuItem mniVersion = new JMenuItem(CommonConstant.MENUITEM_VERSION);

        // 難易度選択メニューアイテム生成
        JRadioButtonMenuItem mniDifficulty1 = new JRadioButtonMenuItem(CommonConstant.MENUITEM_DIFFICULTY_1);
        JRadioButtonMenuItem mniDifficulty2 = new JRadioButtonMenuItem(CommonConstant.MENUITEM_DIFFICULTY_2);
        JRadioButtonMenuItem mniDifficulty3 = new JRadioButtonMenuItem(CommonConstant.MENUITEM_DIFFICULTY_3);
        JRadioButtonMenuItem mniDifficulty4 = new JRadioButtonMenuItem(CommonConstant.MENUITEM_DIFFICULTY_4);
        // 初期選択を初級に設定
        mniDifficulty1.setSelected(true);
        // カスタムは未実装なので選択不可
        mniDifficulty4.setEnabled(false);

        ButtonGroup group = new ButtonGroup();
        group.add(mniDifficulty1);
        group.add(mniDifficulty2);
        group.add(mniDifficulty3);
        group.add(mniDifficulty4);

        // メニューアイテムにアクションリスナーを設定
        mniNew.addActionListener(this);
        mniExit.addActionListener(this);
        mniDifficulty1.addActionListener(this);
        mniDifficulty2.addActionListener(this);
        mniDifficulty3.addActionListener(this);
        mniDifficulty4.addActionListener(this);

        // メニューにメニューアイテムを追加
        mnFile.add(mniNew);
        mnFile.addSeparator();
        mnFile.add(mniDifficulty1);
        mnFile.add(mniDifficulty2);
        mnFile.add(mniDifficulty3);
        mnFile.add(mniDifficulty4);
        mnFile.addSeparator();
        mnFile.add(mniExit);
        mnHelp.add(mniVersion);

        // メニューバーにメニューを追加
        menubar.add(mnFile);
        menubar.add(mnHelp);

        // メニューバーをフレームに追加
        setJMenuBar(menubar);
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

        switch(command) {
        case CommonConstant.MENUITEM_NEW:
            // 選択中の難易度で初期化
            break;
        case CommonConstant.MENUITEM_DIFFICULTY_1:
            setMainPanel(Difficulty.BEGINNER);
            break;
        case CommonConstant.MENUITEM_DIFFICULTY_2:
            setMainPanel(Difficulty.MIDDLE);
            break;
        case CommonConstant.MENUITEM_DIFFICULTY_3:
            setMainPanel(Difficulty.ADVANCED);
            break;
        case CommonConstant.MENUITEM_DIFFICULTY_4:
            break;
        case CommonConstant.MENUITEM_EXIT:
            System.exit(0);
            break;
        case CommonConstant.MENUITEM_VERSION:
            JOptionPane.showMessageDialog(this,
                    "バージョンID" + ConfigReader.getInstance().getProperty(CommonConstant.KEY_VERSIONID));
            break;
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
