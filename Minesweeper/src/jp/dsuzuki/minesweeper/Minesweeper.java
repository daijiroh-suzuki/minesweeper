package jp.dsuzuki.minesweeper;

import java.awt.Container;

import javax.swing.JFrame;

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
		
		// ���C���p�l���𐶐����ăt���[���ɒǉ�
		MainPanel panel = new MainPanel(CommonConstant.MIDDLE_INDEX);
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
