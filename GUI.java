package aOverload;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setAlwaysOnTop(true);
		setTitle("aOverload");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 272, 266);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAoverload = new JLabel("aOverload");
		lblAoverload.setFont(new Font("Impact", Font.PLAIN, 19));
		lblAoverload.setBounds(84, 11, 102, 30);
		contentPane.add(lblAoverload);
		
		JLabel lblScriptByAtex = new JLabel("script by Atex");
		lblScriptByAtex.setBounds(84, 39, 82, 14);
		contentPane.add(lblScriptByAtex);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		btnStart.setBounds(84, 189, 89, 23);
		contentPane.add(btnStart);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 61, 236, 14);
		contentPane.add(separator);
		
		JTextArea txtrHowTo = new JTextArea();
		txtrHowTo.setBackground(SystemColor.menu);
		txtrHowTo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrHowTo.setText("How to:\r\n - have all items in one bank tab\r\n - make sure that tab is active\r\n - the script stops once it runs out of any supply\r\n\r\nFollow updates on the forum thread");
		txtrHowTo.setBounds(10, 71, 236, 107);
		contentPane.add(txtrHowTo);
	}
}
