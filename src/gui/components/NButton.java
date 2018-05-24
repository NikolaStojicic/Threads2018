package gui.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import gui.MainWindow;

public class NButton extends JButton {
	private static final String FILE_PATH = "/gui/res/images/";
	private static final String DOWN_PNG = "_down.png";
	private static final String UP_PNG = "_up.png";
	private static final long serialVersionUID = 1L;
	private Color C_PRESSED = new Color(44, 66, 81);
	private Color C_ROLLOVER = new Color(0, 55, 95);
	private Color C_UP = new Color(26, 147, 111);

	private String nameofComponent;

	public String getNameofComponent() {
		return nameofComponent;
	}

	public void setNameofComponent(String nameofComponent) {
		this.nameofComponent = nameofComponent;
	}

	public NButton(String name) {
		this.nameofComponent = name;
		this.setGuiComponentListener();
		setBorderPainted(false);
		setFocusPainted(false);
		changeListener();
		setContentAreaFilled(false);
		setOpaque(true);
		setBackground(C_UP);
	}

	void swap() {
		Color c = C_PRESSED;
		C_PRESSED = C_UP;
		C_UP = c;
	}

	void setGuiComponentListener() {
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setImage(getNameofComponent().split("_")[0] + UP_PNG);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setImage(getNameofComponent().split("_")[0] + DOWN_PNG);
			}
		});

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				swap();
			}
		});
	}

	private String getRawName(String image) {
		return (image.contains(UP_PNG)) ? image.split(UP_PNG)[0] : image.split(DOWN_PNG)[0];
	}

	void guiComponentAnim() {
		String image = getNameofComponent();
		if (!image.contains(UP_PNG) && !image.contains(DOWN_PNG))
			return;
		String imageName = getRawName(image);
		if (image.contains(UP_PNG)) {
			setImage(imageName + DOWN_PNG);
		} else {
			setImage(imageName + UP_PNG);
		}
	}

	private void setImage(String image) {
		setGuiComponentImage(image, this.getSize().width, this.getSize().height);
	}

	void changeListener() {
		this.getModel().addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if (getModel().isPressed()) {
					setBackground(C_PRESSED);
				} else if (getModel().isRollover()) {
					setBackground(C_ROLLOVER);
				} else {
					setBackground(C_UP);
				}
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		if (getModel().isRollover())
			guiComponentAnim();
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
	}

	public void setGuiImage() {
		setGuiComponentImage(getNameofComponent(), this.getSize().width, this.getSize().height);
	}

	private void setGuiComponentImage(String imageName, int x, int y) {
		try {
			Icon icon = new ImageIcon(new javax.swing.ImageIcon(MainWindow.class.getResource(FILE_PATH + imageName))
					.getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH));
			this.setIcon(icon);
			setNameofComponent(imageName);
		} catch (Exception e) {
			System.out.println(this.getNameofComponent() + ": Missing proper icon images...");
			e.printStackTrace();
		}
	}

}
