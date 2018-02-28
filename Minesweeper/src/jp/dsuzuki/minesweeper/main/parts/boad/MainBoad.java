package jp.dsuzuki.minesweeper.main.parts.boad;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import jp.dsuzuki.minesweeper.MainPanel;
import jp.dsuzuki.minesweeper.common.Difficulty;

public class MainBoad extends JPanel implements MouseListener {

    /** 盤面の状態 */
    private static final int BOAD_STATE_NONE = 0;
    private static final int BOAD_STATE_BOMB = 9;
    private static final int BOAD_STATE_WALL = 99;

    /** カバーの状態 */
    private static final int COVER_STATE_NONE = -1;
    private static final int COVER_STATE_PULL = 0;
    //private static final int COVER_STATE_DENT = 1;
    private static final int COVER_STATE_FLAG = 2;
    private static final int COVER_STATE_QUES = 3;

    /** 周囲8方向の参照x方向 */
    private static final int[] DIRECTION_X = { 0,  1, 1, 1, 0, -1, -1, -1};
    /** 周囲8方向の参照y方向 */
    private static final int[] DIRECTION_Y = {-1, -1, 0, 1, 1,  1,  0, -1};

    /** 盤面画像読み込み位置用定数  */
    private static final int IMAGE_BOAD  = 0;
    /** カバー画像読み込み位置用定数 */
    private static final int IMAGE_COVER = 30;

    /** x方向のタイル数 */
    private int tileX;
    /** y方向のタイル数 */
    private int tileY;
    /** タイルの一辺の大きさ */
    private int tileSize;
    /** 爆弾の個数 */
    private int bombNum;
    /** ゲームクリア条件 */
    private int clearNum;

    /** 盤面 */
    private int[][] boad;

    /** カバー */
    private int[][] cover;

    /** 初回クリックフラグ */
    private boolean clickFlag;

    /** マウスドラッグフラグ */
    private boolean dragFlag;

    /** 盤面画像イメージ */
    private Image image;

    /**
     * コンストラクタ
     */
    public MainBoad(Difficulty difficulty) {

        // x方向のタイル数を取得
        tileX = difficulty.TILE_X;
        // y方向のタイル数を取得
        tileY = difficulty.TILE_Y;
        // タイルの一辺の大きさを取得
        tileSize = difficulty.TILE_SIZE;
        // 爆弾の個数を取得
        bombNum = difficulty.BOMB_NUM;
        // ゲームクリア条件を設定
        clearNum = (tileX * tileY) - bombNum;

        // パネルの推奨サイズを設定
        setPreferredSize(new Dimension(tileX * tileSize, tileY * tileSize));

        // 盤面の初期化を行う
        init();

        // 画像の読み込みを行う
        loadImage();

        // MouseListenerを設定
        addMouseListener(this);
    }

    /**
     * 盤面の初期化処理を行う。
     */
    public void init() {

        // 盤面の配列を生成
        boad  = new int[tileY+2][tileX+2];
        // カバーの配列を生成
        cover = new int[tileY+2][tileX+2];

        for(int i=0; i<boad.length; i++) {
            for(int j=0; j<boad[i].length; j++) {
                // 盤面の初期化
                boad[i][j] = BOAD_STATE_NONE;
                // カバーの初期化
                cover[i][j] = COVER_STATE_PULL;
            }
        }

        // 盤面に壁を設定
        for(int i=0; i<boad.length; i++) {
            boad[i][0] = BOAD_STATE_WALL;
            boad[i][boad[i].length-1] = BOAD_STATE_WALL;
        }
        for(int j=0; j<boad[0].length; j++) {
            boad[0][j] = BOAD_STATE_WALL;
            boad[boad.length-1][j] = BOAD_STATE_WALL;
        }

        // 初回クリックフラグを初期化する
        clickFlag = false;

        System.out.println("盤面の初期化完了");

        repaint();
    }

    /**
     * 爆弾をセットする。
     *
     * @param x 初回クリックx座標
     * @param y 初回クリックy座標
     */
    private void setBomb(int x, int y) {

        Random rand = new Random();

        for(int i=0; i<bombNum; i++) {
            // 乱数で爆弾のx,y座標を取得
            int bombX = rand.nextInt(tileX - 2) + 1;
            int bombY = rand.nextInt(tileY - 2) + 1;

            // 取得したx,y座標が初回クリック座標の場合は取得し直し
            if(bombX == x && bombY == y) {
                System.out.println("x:" + bombX + ",y:" + bombY + "は初回クリック座標なのでやり直します。");
                i--;
                continue;
            }

            // 取得したx,y座標に既に爆弾があった場合は取得し直し
            if(boad[bombY][bombX] == BOAD_STATE_BOMB) {
                System.out.println("x:" + bombX + ",y:" + bombY + "に既に爆弾が設定されているのでやり直します。");
                i--;
                continue;
            }
            // 爆弾を盤面に設定
            boad[bombY][bombX] = BOAD_STATE_BOMB;

            System.out.println("爆弾をx:" + bombX + ",y:" + bombY + "にセットしました。");
        }
    }

    /**
     * 盤面の値を計算する。
     */
    private void calcBoad() {

        System.out.println("盤面の値の計算を開始します。");

        for(int i=1; i<boad.length-1; i++) {
            for(int j=1; j<boad[i].length-1; j++) {

                // 爆弾のマスはスキップ
                if(BOAD_STATE_BOMB == boad[i][j]) {
                    continue;
                }

                // 周囲8方向の爆弾の個数を数える
                int bomb_num = 0;
                for(int k=0; k<8; k++) {
                    if(boad[i+DIRECTION_Y[k]][j+DIRECTION_X[k]] == BOAD_STATE_BOMB) {
                        bomb_num++;
                    }
                }
                // 盤面に爆弾の個数をセットする
                boad[i][j] = bomb_num;
            }
        }

        System.out.println("盤面の値の計算が完了しました。");
    }

    /**
     * 描画処理を行う。
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int i=1; i<boad.length-1; i++) {
            for(int j=1; j<boad[i].length-1; j++) {

                if(cover[i][j] == COVER_STATE_NONE) {
                    // 盤面を描画
                    g.drawImage(image,
                            tileSize * j - tileSize,
                            tileSize * i - tileSize,
                            tileSize * j,
                            tileSize * i,
                            boad[i][j] * tileSize,
                            IMAGE_BOAD,
                            boad[i][j] * tileSize + tileSize,
                            IMAGE_BOAD + tileSize,
                            null);
                } else {
                    // カバーを描画
                    g.drawImage(image,
                            tileSize * j - tileSize,
                            tileSize * i - tileSize,
                            tileSize * j,
                            tileSize * i,
                            cover[i][j] * tileSize,
                            IMAGE_COVER,
                            cover[i][j] * tileSize + tileSize,
                            IMAGE_COVER + tileSize,
                            null);
                }
            }
        }
    }

    /**
     * ピクセル座標をグリッド座標へ変換する
     *
     * @param point ピクセル座標
     * @return グリッド座標
     */
    private int pixelToGrid(int point) {
        return (point / tileSize) + 1;
    }

    /**
     * 隣接するゼロのマスを全てオープンする。
     *
     * @param x x座標
     * @param y y座標
     */
    private void openZeroTile(int x, int y) {

        if(cover[y][x] == COVER_STATE_FLAG) {
            MainPanel.getCounter().countUp();  // カウンタを加算
        }

        // カバーをオープンする
        cover[y][x] = COVER_STATE_NONE;

        // 周囲8方向を走査する
        for(int i=0; i<8; i++) {

            // カバーがオープンされていない場合
            if(cover[y+DIRECTION_Y[i]][x+DIRECTION_X[i]] != COVER_STATE_NONE) {

                // 走査マスの値がゼロの場合
                if(boad[y+DIRECTION_Y[i]][x+DIRECTION_X[i]] == BOAD_STATE_NONE) {
                    openZeroTile(x+DIRECTION_X[i], y+DIRECTION_Y[i]);
                } else {
                    cover[y+DIRECTION_Y[i]][x+DIRECTION_X[i]] = COVER_STATE_NONE;
                }
            }
        }
    }

    /**
     * ゲームオーバー処理を行う。
     */
    private void gameOver() {

        // カバーを全てオープンする
        for(int i=0; i<cover.length; i++) {
            for(int j=0; j<cover[i].length; j++) {
                cover[i][j] = COVER_STATE_NONE;
            }
        }

        // タイマーを停止
        MainPanel.getTimer().stop();
        // ボタン表示を変更
        MainPanel.getButton().setText(InitButton.BUTTON_GAME_OVER);
    }

    /**
     * ゲームクリア処理
     */
    private void gameClear() {

        // オープンされているカバー数を数える
        int cnt = 0;
        for(int i=1; i<cover.length-1; i++) {
            for(int j=1; j<cover[i].length-1; j++) {
                if(cover[i][j] == COVER_STATE_NONE) cnt++;
            }
        }

        System.out.println("オープンされているカバー数：" + cnt);
        System.out.println("クリア条件：" + clearNum);

        // オープンされていカバー数がクリア条件を満たしていない場合
        if(cnt < clearNum) {
            return;
        }

        // タイマーを停止
        MainPanel.getTimer().stop();
        // ボタン表示を変更
        MainPanel.getButton().setText(InitButton.BUTTON_GAME_CLEAR);
    }

    /**
     * マウスボタンが押された時の処理
     */
    @Override
    public void mousePressed(MouseEvent e) {

        Point point = e.getPoint();
        System.out.println("mousePressed x:" + pixelToGrid((int)point.getX()) + ", y:" + pixelToGrid((int)point.getY()));

        // ドラッグフラグをオンにする
        dragFlag = true;
    }

    /**
     * マウスボタンが離された時の処理
     */
    @Override
    public void mouseReleased(MouseEvent e) {

        // グリッド座標を取得
        Point point = e.getPoint();
        int x = pixelToGrid((int)point.getX());
        int y = pixelToGrid((int)point.getY());
        System.out.println("mouseReleased x:" + x + ", y:" + y);

        // ボタンの種類を取得
        int button = e.getButton();

        // ドラッグフラグがオフの場合は処理をスキップする
        if(!dragFlag) {
            return;
        }

        // 左クリックの場合
        if(MouseEvent.BUTTON1 == button) {

            // 初回クリックの場合
            if(!clickFlag) {
                // 爆弾をセットする
                setBomb(x, y);
                // 盤面の値を計算する
                calcBoad();
                // タイマーを開始する
                MainPanel.getTimer().start();
                // デバック情報を出力
                printBoad();

                clickFlag = true;
            }

            // 選択マスの値がゼロの場合
            if(boad[y][x] == BOAD_STATE_NONE) {
                openZeroTile(x, y);
                // ゲームクリア処理
                gameClear();

            // 選択マスが爆弾の場合
            } else if(boad[y][x] == BOAD_STATE_BOMB) {
                // ゲームオーバー処理
                gameOver();

            // 上記以外の場合
            } else {
                if(cover[y][x] == COVER_STATE_FLAG) {
                    MainPanel.getCounter().countUp(); // カウンタを加算
                }
                // カバーをオープンする
                cover[y][x] = COVER_STATE_NONE;
                // ゲームクリア処理
                gameClear();
            }

        // 右クリックの場合
        } else if(MouseEvent.BUTTON3 == button) {

            if(cover[y][x] == COVER_STATE_PULL) {
                cover[y][x] = COVER_STATE_FLAG;
                MainPanel.getCounter().countDown(); // カウンタを減算

            } else if(cover[y][x] == COVER_STATE_FLAG) {
                cover[y][x] = COVER_STATE_QUES;
                MainPanel.getCounter().countUp(); // カウンタを加算

            } else if(cover[y][x] == COVER_STATE_QUES) {
                cover[y][x] = COVER_STATE_PULL;
            }
        }

        // ドラッグフラグをオフにする
        dragFlag = false;

        repaint();
    }

    /**
     * マウスがクリックされた時の処理
     */
    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * マウスポインタがコンポーネント領域に入った時の処理
     */
    @Override
    public void mouseEntered(MouseEvent e) {

        if(!dragFlag) {
            dragFlag = true;
        }
    }

    /**
     * マウスポインタがコンポーネント領域から出た時の処理
     */
    @Override
    public void mouseExited(MouseEvent e) {

        dragFlag = false;
    }

    /**
     * 画像をロードする
     */
    private void loadImage() {
        ImageIcon icon = new ImageIcon("./image/tile.gif");
        image = icon.getImage();
    }

    /**
     * 盤面の値をコンソールに出力する（デバック用）
     */
    private void printBoad() {

        for(int i=0; i<boad.length; i++) {
            for(int j=0; j<boad[i].length; j++) {
                System.out.printf("%2d ", boad[i][j]);
            }
            System.out.println();
        }
    }
}
