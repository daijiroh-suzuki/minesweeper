package jp.dsuzuki.minesweeper.common;

public class CommonConstant {
	
	/*
	 * 初級：9×9のマスに10個の地雷
	 * 中級：16×16のマスに40個の地雷
	 * 上級：30×16のマスに99個の地雷
	 */
	
	/** y方向のタイル数 */
	public static final int TILE_Y = 16;
	/** X方向のタイル数 */
	public static final int TILE_X = 16;
	
	/** タイルの一辺の大きさ */
	public static final int TILE_SIZE = 30;
	
	/** 爆弾の個数 */
	public static final int BOMB_NUM = 40;
	
	/** ゲームクリア条件 */
	public static final int CLEAR_NUM = (TILE_Y * TILE_X) - BOMB_NUM;
}
