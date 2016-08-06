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
	
	/** �Ֆ� */
	private MainBoad boad;
	
	/**
	 * �R���X�g���N�^
	 */
	public MainPanel(int index) {
		
		Map<String, Integer> settingMap = CommonConstant.SETTING_LIST.get(index);
		// �p�l���̕����擾
		int width = settingMap.get(CommonConstant.TILE_X) * settingMap.get(CommonConstant.TILE_SIZE) + 20;
		// �p�l���̍������擾
		int height = settingMap.get(CommonConstant.TILE_Y) * settingMap.get(CommonConstant.TILE_SIZE) + 60;
		
		// �p�l���̐����T�C�Y��ݒ�Apack()����Ƃ��ɕK�v
		setPreferredSize(new Dimension(width, height));
		
		// �{�^���𐶐�
		JButton button = new JButton(CommonConstant.BUTTON_INIT);
		// �ՖʃN���X�𐶐�
		boad = new MainBoad(button, index);
		
		// �{�^���̃A�N�V�������X�i�[��ݒ�
		button.addMouseListener(
				new MouseAdapter() {
					public void mouseReleased(MouseEvent e) {
						JButton btn = (JButton)e.getSource();
						btn.setText(CommonConstant.BUTTON_INIT);
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
