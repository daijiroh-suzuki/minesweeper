package jp.dsuzuki.minesweeper.main.parts.boad;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import jp.dsuzuki.minesweeper.common.CommonConstant;

public class MainBoad extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	/** �Ֆʂ̏�� */
	private static final int BOAD_STATE_NONE = 0;
	private static final int BOAD_STATE_BOMB = 9;
	private static final int BOAD_STATE_WALL = 99;

	/** �J�o�[�̏�� */
	private static final int COVER_STATE_NONE = -1;
	private static final int COVER_STATE_PULL = 0;
	//private static final int COVER_STATE_DENT = 1;
	private static final int COVER_STATE_FLAG = 2;
	private static final int COVER_STATE_QUES = 3;

	/** ����8�����̎Q��x���� */
	private static final int[] DIRECTION_X = { 0,  1, 1, 1, 0, -1, -1, -1};
	/** ����8�����̎Q��y���� */
	private static final int[] DIRECTION_Y = {-1, -1, 0, 1, 1,  1,  0, -1};

	/** �Ֆʉ摜�ǂݍ��݈ʒu�p�萔  */
	private static final int IMAGE_BOAD  = 0;
	/** �J�o�[�摜�ǂݍ��݈ʒu�p�萔 */
	private static final int IMAGE_COVER = 30;

	/** ���C���{�^�� */
	private JButton mainButton;

	/** x�����̃^�C���� */
	private int tileX;
	/** y�����̃^�C���� */
	private int tileY;
	/** �^�C���̈�ӂ̑傫�� */
	private int tileSize;
	/** ���e�̌� */
	private int bombNum;
	/** �Q�[���N���A���� */
	private int clearNum;

	/** �Ֆ� */
	private int[][] boad;

	/** �J�o�[ */
	private int[][] cover;

	/** ����N���b�N�t���O */
	private boolean clickFlag;

	/** �}�E�X�h���b�O�t���O */
	private boolean dragFlag;

	/** �Ֆʉ摜�C���[�W */
	private Image image;

	/**
	 * �R���X�g���N�^
	 */
	public MainBoad(JButton btn, int index) {

		// �ݒ�MAP���擾
		Map<String, Integer> settingMap = CommonConstant.SETTING_LIST.get(index);

		// x�����̃^�C�������擾
		tileX = settingMap.get(CommonConstant.TILE_X);
		// y�����̃^�C�������擾
		tileY = settingMap.get(CommonConstant.TILE_Y);
		// �^�C���̈�ӂ̑傫�����擾
		tileSize = settingMap.get(CommonConstant.TILE_SIZE);
		// ���e�̌����擾
		bombNum = settingMap.get(CommonConstant.BOMB_NUM);
		// �Q�[���N���A������ݒ�
		clearNum = (tileX * tileY) - bombNum;

		// �p�l���̐����T�C�Y��ݒ�
		setPreferredSize(new Dimension(tileX * tileSize, tileY * tileSize));

		// ���C���{�^����ݒ�
		mainButton = btn;

		// �Ֆʂ̏��������s��
		init();

		// �摜�̓ǂݍ��݂��s��
		loadImage();

		// MouseListener��ݒ�
		addMouseListener(this);
	}

	/**
	 * �Ֆʂ̏������������s���B
	 */
	public void init() {

		// �Ֆʂ̔z��𐶐�
		boad  = new int[tileY+2][tileX+2];
		// �J�o�[�̔z��𐶐�
		cover = new int[tileY+2][tileX+2];

		for(int i=0; i<boad.length; i++) {
			for(int j=0; j<boad[i].length; j++) {
				// �Ֆʂ̏�����
				boad[i][j] = BOAD_STATE_NONE;
				// �J�o�[�̏�����
				cover[i][j] = COVER_STATE_PULL;
			}
		}

		// �Ֆʂɕǂ�ݒ�
		for(int i=0; i<boad.length; i++) {
			boad[i][0] = BOAD_STATE_WALL;
			boad[i][boad[i].length-1] = BOAD_STATE_WALL;
		}
		for(int j=0; j<boad[0].length; j++) {
			boad[0][j] = BOAD_STATE_WALL;
			boad[boad[0].length-1][j] = BOAD_STATE_WALL;
		}

		// ����N���b�N�t���O������������
		clickFlag = false;

		System.out.println("�Ֆʂ̏���������");

		repaint();
	}

	/**
	 * ���e���Z�b�g����B
	 *
	 * @param x ����N���b�Nx���W
	 * @param y ����N���b�Ny���W
	 */
	private void setBomb(int x, int y) {

		Random rand = new Random();

		for(int i=0; i<bombNum; i++) {
			// �����Ŕ��e��x,y���W���擾
			int bombX = rand.nextInt(tileX - 2) + 1;
			int bombY = rand.nextInt(tileY - 2) + 1;

			// �擾����x,y���W������N���b�N���W�̏ꍇ�͎擾������
			if(bombX == x && bombY == y) {
				System.out.println("x:" + bombX + ",y:" + bombY + "�͏���N���b�N���W�Ȃ̂ł�蒼���܂��B");
				i--;
				continue;
			}

			// �擾����x,y���W�Ɋ��ɔ��e���������ꍇ�͎擾������
			if(boad[bombY][bombX] == BOAD_STATE_BOMB) {
				System.out.println("x:" + bombX + ",y:" + bombY + "�Ɋ��ɔ��e���ݒ肳��Ă���̂ł�蒼���܂��B");
				i--;
				continue;
			}
			// ���e��Ֆʂɐݒ�
			boad[bombY][bombX] = BOAD_STATE_BOMB;

			System.out.println("���e��x:" + bombX + ",y:" + bombY + "�ɃZ�b�g���܂����B");
		}
	}

	/**
	 * �Ֆʂ̒l���v�Z����B
	 */
	private void calcBoad() {

		System.out.println("�Ֆʂ̒l�̌v�Z���J�n���܂��B");

		for(int i=1; i<boad.length-1; i++) {
			for(int j=1; j<boad[i].length-1; j++) {

				// ���e�̃}�X�̓X�L�b�v
				if(BOAD_STATE_BOMB == boad[i][j]) {
					continue;
				}

				// ����8�����̔��e�̌��𐔂���
				int bomb_num = 0;
				for(int k=0; k<8; k++) {
					if(boad[i+DIRECTION_Y[k]][j+DIRECTION_X[k]] == BOAD_STATE_BOMB) {
						bomb_num++;
					}
				}
				// �Ֆʂɔ��e�̌����Z�b�g����
				boad[i][j] = bomb_num;
			}
		}

		System.out.println("�Ֆʂ̒l�̌v�Z���������܂����B");
	}

	/**
	 * �`�揈�����s���B
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for(int i=1; i<boad.length-1; i++) {
			for(int j=1; j<boad[i].length-1; j++) {

				if(cover[i][j] == COVER_STATE_NONE) {
					// �Ֆʂ�`��
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
					// �J�o�[��`��
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
	 * �s�N�Z�����W���O���b�h���W�֕ϊ�����
	 *
	 * @param point �s�N�Z�����W
	 * @return �O���b�h���W
	 */
	private int pixelToGrid(int point) {
		return (point / tileSize) + 1;
	}

	/**
	 * �ċA�I�ɌĂяo����āA�אڂ���[���̃}�X��S�ăI�[�v������B
	 *
	 * @param x x���W
	 * @param y y���W
	 */
	private void openZeroTile(int x, int y) {

		// �J�o�[���I�[�v������
		cover[y][x] = COVER_STATE_NONE;

		// ����8�����𑖍�����
		for(int i=0; i<8; i++) {

			// �J�o�[���I�[�v������Ă��Ȃ��ꍇ
			if(cover[y+DIRECTION_Y[i]][x+DIRECTION_X[i]] != COVER_STATE_NONE) {

				// �����}�X�̒l���[���̏ꍇ
				if(boad[y+DIRECTION_Y[i]][x+DIRECTION_X[i]] == BOAD_STATE_NONE) {
					openZeroTile(x+DIRECTION_X[i], y+DIRECTION_Y[i]);
				} else {
					cover[y+DIRECTION_Y[i]][x+DIRECTION_X[i]] = COVER_STATE_NONE;
				}
			}
		}
	}

	/**
	 * �Q�[���I�[�o�[�������s���B
	 */
	private void gameOver() {

		// �J�o�[��S�ăI�[�v������
		for(int i=0; i<cover.length; i++) {
			for(int j=0; j<cover[i].length; j++) {
				cover[i][j] = COVER_STATE_NONE;
			}
		}

		// �{�^���\����ύX
		mainButton.setText(CommonConstant.BUTTON_GAME_OVER);
	}

	/**
	 * �Q�[���N���A����
	 */
	private void gameClear() {

		// �I�[�v������Ă���J�o�[���𐔂���
		int cnt = 0;
		for(int i=1; i<cover.length-1; i++) {
			for(int j=1; j<cover[i].length-1; j++) {
				if(cover[i][j] == COVER_STATE_NONE) cnt++;
			}
		}

		System.out.println("�I�[�v������Ă���J�o�[���F" + cnt);
		System.out.println("�N���A�����F" + clearNum);

		// �I�[�v������Ă��J�o�[�����N���A�����𖞂����Ă��Ȃ��ꍇ
		if(cnt < clearNum) {
			return;
		}

		// �{�^���\����ύX
		mainButton.setText(CommonConstant.BUTTON_GAME_CLEAR);
	}

	/**
	 * �}�E�X�{�^���������ꂽ���̏���
	 */
	public void mousePressed(MouseEvent e) {

		Point point = e.getPoint();
		System.out.println("mousePressed x:" + pixelToGrid((int)point.getX()) + ", y:" + pixelToGrid((int)point.getY()));

		// �h���b�O�t���O���I���ɂ���
		dragFlag = true;
	}

	/**
	 * �}�E�X�{�^���������ꂽ���̏���
	 */
	public void mouseReleased(MouseEvent e) {

		// �O���b�h���W���擾
		Point point = e.getPoint();
		int x = pixelToGrid((int)point.getX());
		int y = pixelToGrid((int)point.getY());
		System.out.println("mouseReleased x:" + x + ", y:" + y);

		// �{�^���̎�ނ��擾
		int button = e.getButton();

		// �h���b�O�t���O���I�t�̏ꍇ�͏������X�L�b�v����
		if(!dragFlag) {
			return;
		}

		// ���N���b�N�̏ꍇ
		if(MouseEvent.BUTTON1 == button) {

			// ����N���b�N�̏ꍇ
			if(!clickFlag) {
				// ���e���Z�b�g����
				setBomb(x, y);
				// �Ֆʂ̒l���v�Z����
				calcBoad();
				// �f�o�b�N�����o��
				printBoad();

				clickFlag = true;
			}

			// �I���}�X�̒l���[���̏ꍇ
			if(boad[y][x] == BOAD_STATE_NONE) {
				openZeroTile(x, y);
				// �Q�[���N���A����
				gameClear();

			// �I���}�X�����e�̏ꍇ
			} else if(boad[y][x] == BOAD_STATE_BOMB) {
				// �Q�[���I�[�o�[����
				gameOver();

			// ��L�ȊO�̏ꍇ
			} else {
				// �J�o�[���I�[�v������
				cover[y][x] = COVER_STATE_NONE;
				// �Q�[���N���A����
				gameClear();
			}

		// �E�N���b�N�̏ꍇ
		} else if(MouseEvent.BUTTON3 == button) {

			if(cover[y][x] == COVER_STATE_PULL) {
				cover[y][x] = COVER_STATE_FLAG;

			} else if(cover[y][x] == COVER_STATE_FLAG) {
				cover[y][x] = COVER_STATE_QUES;

			} else if(cover[y][x] == COVER_STATE_QUES) {
				cover[y][x] = COVER_STATE_PULL;
			}
		}

		// �h���b�O�t���O���I�t�ɂ���
		dragFlag = false;

		repaint();
	}

	/**
	 * �}�E�X���N���b�N���ꂽ���̏���
	 */
	public void mouseClicked(MouseEvent e) {}

	/**
	 * �}�E�X�|�C���^���R���|�[�l���g�̈�ɓ��������̏���
	 */
	public void mouseEntered(MouseEvent e) {

		if(!dragFlag) {
			dragFlag = true;
		}
	}

	/**
	 * �}�E�X�|�C���^���R���|�[�l���g�̈悩��o�����̏���
	 */
	public void mouseExited(MouseEvent e) {

		dragFlag = false;
	}

	/**
	 * �摜�����[�h����
	 */
	private void loadImage() {
		ImageIcon icon = new ImageIcon("./image/tile.gif");
		image = icon.getImage();
	}

	/**
	 * �Ֆʂ̒l���R���\�[���ɏo�͂���i�f�o�b�N�p�j
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
