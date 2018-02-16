package jp.dsuzuki.minesweeper.common;

/**
 * 難易度設定
 *
 * @author daijiroh
 *
 */
public enum Difficulty {

    /** 初級：9×9のマスに10個の爆弾 */
    BEGINNER(9,  9,  30, 10),
    /** 中級：16×16のマスに40個の爆弾 */
    MIDDLE  (16, 16, 30, 40),
    /** 上級：30×16のマスに40個の爆弾 */
    ADVANCED(30, 16, 30, 99);

    /** x方向のタイル数 */
    public final int TILE_X;
    /** y方向のタイル数 */
    public final int TILE_Y;
    /** タイルの一辺の大きさ */
    public final int TILE_SIZE;
    /** 爆弾の個数 */
    public final int BOMB_NUM;

    /**
     * コンストラクタ
     *
     * @param tileX x方向のタイル数
     * @param tileY y方向のタイル数
     * @param tileSize タイルの一辺の大きさ
     * @param bombNum 爆弾の個数
     */
    private Difficulty(int tileX, int tileY, int tileSize, int bombNum) {

        this.TILE_X = tileX;
        this.TILE_Y = tileY;
        this.TILE_SIZE = tileSize;
        this.BOMB_NUM = bombNum;
    }
}
