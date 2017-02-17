package jp.dsuzuki.minesweeper.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ���ʒ萔�N���X
 *
 * @author daijiroh
 *
 */
public class CommonConstant {

	/*
	 * �����F9�~9�̃}�X��10�̒n��
	 * �����F16�~16�̃}�X��40�̒n��
	 * �㋉�F30�~16�̃}�X��99�̒n��
	 */

	/** �t���[���^�C�g�� */
	public static final String FRAME_TITLE = "�}�C���X�C�[�p�[";

	/** �{�^�����x���F�����l */
	public static final String BUTTON_INIT = "(�E�Q�E)";
	/** �{�^�����x���F�Q�[���I�[�o�[ */
	public static final String BUTTON_GAME_OVER = "(�L��֥`) ���ް�";
	/** �{�^�����x���F�Q�[���N���A */
	public static final String BUTTON_GAME_CLEAR = "(`��֥�L)�����";

	/** �ݒ胊�X�g */
	public static final List<Map<String, Integer>> SETTING_LIST = new ArrayList<Map<String, Integer>>();

	/** �����ݒ�C���f�b�N�X */
	public static final int BEGINNER_INDEX = 0;
	/** �����ݒ�C���f�b�N�X */
	public static final int MIDDLE_INDEX = 1;
	/** �㋉�ݒ�C���f�b�N�X */
	public static final int ADVANCED_INDEX = 2;
	/** �J�X�^���ݒ�C���f�b�N�X */
	public static final int CUSTOM_INDEX = 3;

	/** y�����̃^�C���� */
	public static final String TILE_Y = "TILE_Y";
	/** x�����̃^�C���� */
	public static final String TILE_X = "TILE_X";
	/** �^�C���̈�ӂ̑傫�� */
	public static final String TILE_SIZE = "TILE_SIZE";
	/** ���e�̌� */
	public static final String BOMB_NUM = "BOMB_NUM";

	static {
		// �����F9�~9�̃}�X��10�̒n��
		Map<String, Integer> beginner = new HashMap<String, Integer>();
		beginner.put(CommonConstant.TILE_X,    new Integer(9));
		beginner.put(CommonConstant.TILE_Y,    new Integer(9));
		beginner.put(CommonConstant.TILE_SIZE, new Integer(30));
		beginner.put(CommonConstant.BOMB_NUM,  new Integer(10));
		SETTING_LIST.add(CommonConstant.BEGINNER_INDEX, beginner);

		// �����F16�~16�̃}�X��40�̒n��
		Map<String, Integer> middle = new HashMap<String, Integer>();
		middle.put(CommonConstant.TILE_X,    new Integer(16));
		middle.put(CommonConstant.TILE_Y,    new Integer(16));
		middle.put(CommonConstant.TILE_SIZE, new Integer(30));
		middle.put(CommonConstant.BOMB_NUM,  new Integer(40));
		SETTING_LIST.add(CommonConstant.MIDDLE_INDEX, middle);

		// �㋉�F30�~16�̃}�X��99�̒n��
		Map<String, Integer> advanced = new HashMap<String, Integer>();
		advanced.put(CommonConstant.TILE_X,    new Integer(30));
		advanced.put(CommonConstant.TILE_Y,    new Integer(16));
		advanced.put(CommonConstant.TILE_SIZE, new Integer(30));
		advanced.put(CommonConstant.BOMB_NUM,  new Integer(99));
		SETTING_LIST.add(CommonConstant.ADVANCED_INDEX, advanced);
	}
}
