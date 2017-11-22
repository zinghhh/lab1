package lab1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
public class demo {
	private static JFrame jf;
	private static JPanel jp1,jp2,jp3,jp4,jp5,jp6;
	private static JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7,jb8;
	private static JTextField jt1,jt2,jt3,jt4,jt5,jt6;
	private static JTextField bridgewords,newtext,path,walk;
	
	private static Control ctrl = new Control();
	public static void createFrame() {

		//�������
		jf=new JFrame("�ı�����ϵͳ");//����һ������
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp5 = new JPanel();
		jp6 = new JPanel();
		
		jb1=new JButton("���");
		jb2=new JButton("չʾ����ͼ");
		jb3=new JButton("��ѯ���Ӵ�");
		jb4=new JButton("�������ı�");
		jb5=new JButton("��ѯ���·��");
		jb6=new JButton("�������");
		jb7=new JButton("����");
		jb8=new JButton("��ͣ");
		
		jt1 = new JTextField("�ļ���",10);
		jt2 = new JTextField("����һ",5);
		jt3 = new JTextField("���ʶ�",5);
		jt4 = new JTextField("������ı�",15);
		jt5 = new JTextField("��ʼ����",5);
		jt6 = new JTextField("��������",5);
		bridgewords = new JTextField("��ѯ������",25);
		bridgewords.setBackground(Color.LIGHT_GRAY);
		newtext = new JTextField("���ı�",25);
		newtext.setBackground(Color.LIGHT_GRAY);
		path = new JTextField("���·�����",50);
		path.setBackground(Color.lightGray);
		walk = new JTextField("�������·�������",50);
		walk.setBackground(Color.LIGHT_GRAY);
		//�������
		jp1.add(jb1);
		jp1.add(jt1);
		
		jp2.add(jb2);
		
		jp3.add(jb3);
		jp3.add(jt2);
		jp3.add(jt3);
		jp3.add(bridgewords);
		
		jp4.add(jb4);
		jp4.add(jt4);
		jp4.add(newtext);
		
		jp5.add(jb5);
		jp5.add(jt5);
		jp5.add(jt6);
		jp5.add(path);
		
		jp6.add(jb6);
		jp6.add(walk);
		jp6.add(jb7);
		jp6.add(jb8);
		//jp6.setLayout(new GridLayout(2, 1));
		
		jf.add(jp1);
		jf.add(jp2);
		jf.add(jp3);
		jf.add(jp4);
		jf.add(jp5);
		jf.add(jp6);
		//���ò��ֹ�����
		jf.setLayout(new GridLayout(6, 1));
		//���ô���
		jf.setSize(1200, 700);
		jf.setLocationRelativeTo(null);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		jf.setResizable(false);		
	}
	public static void getFile() {
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		int returnval = chooser.showOpenDialog(jb1);
		if(returnval==JFileChooser.APPROVE_OPTION) {
			jt1.setText(chooser.getSelectedFile().getAbsolutePath());
			ctrl.readText(chooser.getSelectedFile().getAbsolutePath());
		}
	}
	public static void getGraph(String filename) {
		ctrl.showDirectedGraph(ctrl.getDotFormat(null),filename);
		}

	public static void queryBridge() {
		String words1=jt2.getText();
		String words2=jt3.getText();
		bridgewords.setText(ctrl.queryBridgeWords(words1, words2));
	}
	public static void getNewText() {
		newtext.setText(ctrl.generateNewText(jt4.getText()));
	}
	public static void getPath() {
		path.setText(ctrl.calcShortestPath(jt5.getText(), jt6.getText()));
	}
	public static void expressGraph(String filename) {
		//MyGraphics g = new MyGraphics(filename+".gif");
		JLabel g = new JLabel();
		try {
			g.setIcon(new ImageIcon(ImageIO.read(new File(filename + ".gif"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JScrollPane sp = new JScrollPane(g);
		sp.setPreferredSize(new Dimension(400,600));
		JFrame jf1 = new JFrame("��ʾͼƬ");
		jf1.setTitle("չʾͼ��");
		jf1.add(sp);
		jf1.setSize(800, 600);
		jf1.setLocationRelativeTo(null);
		jf1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf1.setVisible(true);
		jf1.setResizable(false);
	}
	/*public static class MyGraphics extends JLabel{
		private static final long serialVersionUID = 1L;
		private String filename;
		private Image graph ;
		MyGraphics(String filename){
			this.filename=filename;	
		}
		public void paint(Graphics g) {
			graph = new ImageIcon(filename).getImage();
			g.drawImage(graph, 0, 0, this);
		}
	}*/
	//public static void 
	public static void main(String[] args) {
		createFrame();	
		jb1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getFile();
			}
		});

		jb2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getGraph("E://A//JAVA//out");
				expressGraph("E://A//JAVA//out");
			}
		});
		
		jb3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				queryBridge();
			}
		});
		jb4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getNewText();
			}
		});
		jb5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getPath();
				expressGraph("E://A//JAVA//outshortpath");
			}
		});
		jb6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				walk.setText(ctrl.startWalk());
			}
		});

		jb7.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				walk.setText(ctrl.randomWalk());
			}
		});
		jb8.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.writeText();
			}
		});
	}
}
