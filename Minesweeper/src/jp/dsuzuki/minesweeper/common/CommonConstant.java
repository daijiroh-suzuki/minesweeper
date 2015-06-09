package jp.dsuzuki.minesweeper.common;

public class CommonConstant {
	
	/*
	 * �����F9�~9�̃}�X��10�̒n��
	 * �����F16�~16�̃}�X��40�̒n��
	 * �㋉�F30�~16�̃}�X��99�̒n��
	 */
	
	/** y�����̃^�C���� */
	public static final int TILE_Y = 16;
	/** X�����̃^�C���� */
	public static final int TILE_X = 16;
	
	/** �^�C���̈�ӂ̑傫�� */
	public static final int TILE_SIZE = 30;
	
	/** ���e�̌� */
	public static final int BOMB_NUM = 40;
	
	/** �Q�[���N���A���� */
	public static final int CLEAR_NUM = (TILE_Y * TILE_X) - BOMB_NUM;
}
