package jp.dsuzuki.minesweeper;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import jp.dsuzuki.minesweeper.common.CommonConstant;

/**
 * �}�C���X�C�[�p�[�̃t���[���N���X
 * @author daijiroh
 *
 */
public class Minesweeper extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * �R���X�g���N�^
	 */
	public Minesweeper() {

		// �^�C�g����ݒ肷��
		setTitle(CommonConstant.FRAME_TITLE);

		// ���j���[�o�[�𐶐�
		JMenuBar menubar = new JMenuBar();

		// ���j���[�𐶐�
		JMenu menu1 = new JMenu("�t�@�C��");
		JMenu menu2 = new JMenu("�ҏW");

		// ���j���[�A�C�e���𐶐�
		JMenuItem menuitem1 = new JMenuItem("�V�K");
		JMenuItem menuitem2 = new JMenuItem("�I��");

		// ���j���[�Ƀ��j���[�A�C�e����ǉ�
		menu1.add(menuitem1);
		menu1.add(menuitem2);

		// ���j���[�o�[�Ƀ��j���[��ǉ�
		menubar.add(menu1);
		menubar.add(menu2);

		// ���j���[�o�[���t���[���ɒǉ�
		setJMenuBar(menubar);

		// ���C���p�l���𐶐����ăt���[���ɒǉ�
		MainPanel panel = new MainPanel(CommonConstant.BEGINNER_INDEX);
		Container contentPane = getContentPane();
		contentPane.add(panel);

		// �p�l���T�C�Y�ɍ��킹�ăt���[���T�C�Y�������ݒ�
		pack();
	}

	/**
	 * �G���g���|�C���g
	 *
	 * @param args
	 */
	public static void main(String args[]) {

		// �t���[���𐶐�
		Minesweeper frame = new Minesweeper();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// �t���[������ʂ̒����ɕ\��
		frame.setLocationRelativeTo(null);

		// �t���[���T�C�Y��ύX�s�ɂ���
		frame.setResizable(false);

		// �t���[����\������
		frame.setVisible(true);
	}
}
