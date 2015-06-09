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
	
	/** �p�l���T�C�Y�F�� */
	private static final int WIDTH = CommonConstant.TILE_X * CommonConstant.TILE_SIZE + 20;
	/** �p�l���T�C�Y�F���� */
	private static final int HEIGHT = CommonConstant.TILE_Y * CommonConstant.TILE_SIZE + 60;
	
	/** �Ֆ� */
	private MainBoad boad;
	
	/**
	 * �R���X�g���N�^
	 */
	public MainPanel() {
		
		// �p�l���̐����T�C�Y��ݒ�Apack()����Ƃ��ɕK�v
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		// �{�^���𐶐�
		JButton button = new JButton("(�E�Q�E)");
		// �ՖʃN���X�𐶐�
		boad = new MainBoad(button);
		
		// �{�^���̃A�N�V�������X�i�[��ݒ�
		button.addMouseListener(
				new MouseAdapter() {
					public void mouseReleased(MouseEvent e) {
						JButton btn = (JButton)e.getSource();
						btn.setText("(�E�Q�E)");
						System.out.println("�{�^����������܂����B�Ֆʂ����������܂��B");
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
