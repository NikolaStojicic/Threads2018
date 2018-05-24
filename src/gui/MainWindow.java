package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.components.NButton;
import gui.controller.GUIController;
import gui.util.MoveMouseListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private NButton btnPspng;
	private NButton btnBspng;
	private NButton btnMspng;
	private JButton btnClose;
	public static JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 397, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		//////////////////////////////////////////////////////////////
		MoveMouseListener mml = new MoveMouseListener(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnPspng());
		contentPane.add(getBtnBspng());
		contentPane.add(getBtnMspng());
		contentPane.add(getBtnClose());
		contentPane.addMouseListener(mml);
		contentPane.addMouseMotionListener(mml);
		// contentPane.setBackground(new Color(0, 0, 102));
		contentPane.setBackground(new Color(44, 66, 81));
		contentPane.add(getScrollPane());
		setUndecorated(true);
		setResizable(false);
		Component[] components = contentPane.getComponents();
		for (Component component : components) {
			if (component instanceof NButton)
				((NButton) component).setGuiImage();
		}
		//////////////////////////////////////////////////////////////
	}

	private NButton getBtnPspng() {
		if (btnPspng == null) {
			btnPspng = new NButton("ps_up.png");
			btnPspng.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					GUIController.toggleSinger(GUIController.t.pattiSmith);
				}
			});
			btnPspng.setBounds(259, 37, 133, 160);
		}
		return btnPspng;
	}

	private NButton getBtnBspng() {
		if (btnBspng == null) {
			btnBspng = new NButton("bs_up.png");
			btnBspng.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GUIController.toggleSinger(GUIController.t.bruceSpringsteen);
				}
			});
			btnBspng.setBounds(259, 226, 133, 160);
		}
		return btnBspng;
	}

	private JButton getBtnMspng() {
		if (btnMspng == null) {
			btnMspng = new NButton("ms_up.png");
			btnMspng.setBounds(259, 414, 133, 160);
		}
		return btnMspng;
	}

	private JButton getBtnClose() {
		if (btnClose == null) {
			btnClose = new JButton("CLOSE");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
			btnClose.setBounds(10, 11, 75, 23);
		}
		return btnClose;
	}

	public static JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setEditable(false);
		}
		return textArea;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 37, 239, 537);
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
}
