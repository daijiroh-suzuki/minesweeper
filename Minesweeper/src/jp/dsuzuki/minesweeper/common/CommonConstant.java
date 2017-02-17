package jp.dsuzuki.minesweeper.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 共通定数クラス
 *
 * @author daijiroh
 *
 */
public class CommonConstant {

	/*
	 * 初級：9×9のマスに10個の地雷
	 * 中級：16×16のマスに40個の地雷
	 * 上級：30×16のマスに99個の地雷
	 */

	/** フレームタイトル */
	public static final String FRAME_TITLE = "マインスイーパー";

	/** ボタンラベル：初期値 */
	public static final String BUTTON_INIT = "(・＿・)";
	/** ボタンラベル：ゲームオーバー */
	public static final String BUTTON_GAME_OVER = "(´･ω･`) ｼｮﾎﾞｰﾝ";
	/** ボタンラベル：ゲームクリア */
	public static final String BUTTON_GAME_CLEAR = "(`･ω･´)ｼｬｷｰﾝ";

	/** 設定リスト */
	public static final List<Map<String, Integer>> SETTING_LIST = new ArrayList<Map<String, Integer>>();

	/** 初級設定インデックス */
	public static final int BEGINNER_INDEX = 0;
	/** 中級設定インデックス */
	public static final int MIDDLE_INDEX = 1;
	/** 上級設定インデックス */
	public static final int ADVANCED_INDEX = 2;
	/** カスタム設定インデックス */
	public static final int CUSTOM_INDEX = 3;

	/** y方向のタイル数 */
	public static final String TILE_Y = "TILE_Y";
	/** x方向のタイル数 */
	public static final String TILE_X = "TILE_X";
	/** タイルの一辺の大きさ */
	public static final String TILE_SIZE = "TILE_SIZE";
	/** 爆弾の個数 */
	public static final String BOMB_NUM = "BOMB_NUM";

	static {
		// 初級：9×9のマスに10個の地雷
		Map<String, Integer> beginner = new HashMap<String, Integer>();
		beginner.put(CommonConstant.TILE_X,    new Integer(9));
		beginner.put(CommonConstant.TILE_Y,    new Integer(9));
		beginner.put(CommonConstant.TILE_SIZE, new Integer(30));
		beginner.put(CommonConstant.BOMB_NUM,  new Integer(10));
		SETTING_LIST.add(CommonConstant.BEGINNER_INDEX, beginner);

		// 中級：16×16のマスに40個の地雷
		Map<String, Integer> middle = new HashMap<String, Integer>();
		middle.put(CommonConstant.TILE_X,    new Integer(16));
		middle.put(CommonConstant.TILE_Y,    new Integer(16));
		middle.put(CommonConstant.TILE_SIZE, new Integer(30));
		middle.put(CommonConstant.BOMB_NUM,  new Integer(40));
		SETTING_LIST.add(CommonConstant.MIDDLE_INDEX, middle);

		// 上級：30×16のマスに99個の地雷
		Map<String, Integer> advanced = new HashMap<String, Integer>();
		advanced.put(CommonConstant.TILE_X,    new Integer(30));
		advanced.put(CommonConstant.TILE_Y,    new Integer(16));
		advanced.put(CommonConstant.TILE_SIZE, new Integer(30));
		advanced.put(CommonConstant.BOMB_NUM,  new Integer(99));
		SETTING_LIST.add(CommonConstant.ADVANCED_INDEX, advanced);
	}
}
