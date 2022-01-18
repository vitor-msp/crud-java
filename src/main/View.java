package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import br.com.crud.model.Contato;
import br.com.crud.dao.ContatoDAO;

public class View extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtIdade;
	private JTextField txtEmail;
	private JTextField txtTelefone;
	private JTextField txtDdd;
	private JTable tableContatos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
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
	public View() {
		setTitle("CRUD - Contatos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNome.setBounds(10, 11, 46, 14);
		contentPane.add(lblNome);
		
		JLabel lblIdade = new JLabel("Idade:");
		lblIdade.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIdade.setBounds(10, 39, 46, 14);
		contentPane.add(lblIdade);
		
		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(143, 39, 46, 14);
		contentPane.add(lblEmail);
		
		JLabel lblDdd = new JLabel("DDD:");
		lblDdd.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDdd.setBounds(10, 67, 46, 14);
		contentPane.add(lblDdd);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefone.setBounds(130, 64, 59, 14);
		contentPane.add(lblTelefone);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setHorizontalAlignment(SwingConstants.RIGHT);
		lblId.setBounds(10, 231, 46, 14);
		contentPane.add(lblId);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(62, 8, 421, 20);
		contentPane.add(txtNome);
		
		txtIdade = new JTextField();
		txtIdade.setColumns(10);
		txtIdade.setBounds(62, 36, 58, 20);
		contentPane.add(txtIdade);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(198, 36, 285, 20);
		contentPane.add(txtEmail);
		
		txtDdd = new JTextField();
		txtDdd.setColumns(10);
		txtDdd.setBounds(62, 64, 58, 20);
		contentPane.add(txtDdd);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(199, 61, 143, 20);
		contentPane.add(txtTelefone);
		
		JSpinner spinnerId = new JSpinner();
		spinnerId.setBounds(62, 228, 52, 20);
		contentPane.add(spinnerId);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 126, 473, 90);
		contentPane.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Id:");
		model.addColumn("Nome:");
		model.addColumn("Idade:");
		model.addColumn("E-mail:");
		model.addColumn("DDD:");
		model.addColumn("Telefone:");

		tableContatos = new JTable(model);
		scrollPane.setViewportView(tableContatos);

		tableContatos.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableContatos.getColumnModel().getColumn(1).setPreferredWidth(120);
		tableContatos.getColumnModel().getColumn(2).setPreferredWidth(50);
		tableContatos.getColumnModel().getColumn(3).setPreferredWidth(120);
		tableContatos.getColumnModel().getColumn(4).setPreferredWidth(40);
		tableContatos.getColumnModel().getColumn(5).setPreferredWidth(90);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNome.setText("");
				txtIdade.setText("");
				txtEmail.setText("");
				txtDdd.setText("");
				txtTelefone.setText("");
			}
		});
		btnLimpar.setBounds(394, 60, 89, 23);
		contentPane.add(btnLimpar);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setBounds(394, 227, 89, 23);
		contentPane.add(btnSair);
		
		JButton btnListarContatos = new JButton("Listar Contatos");
		btnListarContatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					List<Contato> contatos = ContatoDAO.readAll();
					model.setNumRows(0);
					for(Contato c: contatos) {
						model.addRow(new Object [] {
							String.valueOf(c.getId()),
							c.getNome(),
							String.valueOf(c.getIdade()),
							c.getEmail(),
							String.valueOf(c.getDdd()),
							String.valueOf(c.getTelefone()),
						});
					}
					
					if(model.getRowCount() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Nenhum contato cadastrado!");
					}
					
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro na consulta de contatos - " + erro);
				}
			}
		});
		btnListarContatos.setBounds(244, 227, 140, 23);
		contentPane.add(btnListarContatos);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Contato contato = new Contato();
					contato.setNome(txtNome.getText());
					contato.setIdade(Integer.parseInt(txtIdade.getText()));
					contato.setEmail(txtEmail.getText());
					contato.setDdd(Integer.parseInt(txtDdd.getText()));
					contato.setTelefone(Integer.parseInt(txtTelefone.getText()));
					ContatoDAO.create(contato);
					btnListarContatos.doClick();
					JOptionPane.showMessageDialog(contentPane, "Contato inserido com sucesso!");
					
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro na inserção do contato - " + erro);
				}
			}
		});
		btnInserir.setBounds(62, 92, 89, 23);
		contentPane.add(btnInserir);
		
		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Contato contato = new Contato();
					contato.setId(Integer.parseInt(spinnerId.getValue().toString()));
					contato.setNome(txtNome.getText());
					contato.setIdade(Integer.parseInt(txtIdade.getText()));
					contato.setEmail(txtEmail.getText());
					contato.setDdd(Integer.parseInt(txtDdd.getText()));
					contato.setTelefone(Integer.parseInt(txtTelefone.getText()));
					ContatoDAO.update(contato);
					btnListarContatos.doClick();
					JOptionPane.showMessageDialog(contentPane, "Contato atualizado com sucesso!");
					
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro na atualização do contato - " + erro);
				}
			}
		});
		btnAtualizar.setBounds(157, 92, 89, 23);
		contentPane.add(btnAtualizar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					ContatoDAO.delete(Integer.parseInt(spinnerId.getValue().toString()));
					btnListarContatos.doClick();
					JOptionPane.showMessageDialog(contentPane, "Contato removido com sucesso!");
					
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro na remoção do contato - " + erro);
				}
			}
		});
		btnDeletar.setBounds(253, 92, 89, 23);
		contentPane.add(btnDeletar);

		JButton btnTrazerContato = new JButton("Trazer Contato");
		btnTrazerContato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Contato contato = ContatoDAO.read(Integer.parseInt(spinnerId.getValue().toString()));
					if(contato != null) {						
						txtNome.setText(contato.getNome());
						txtIdade.setText(String.valueOf(contato.getIdade()));
						txtEmail.setText(contato.getEmail());
						txtDdd.setText(String.valueOf(contato.getDdd()));
						txtTelefone.setText(String.valueOf(contato.getTelefone()));
					}else {						
						JOptionPane.showMessageDialog(contentPane, "Contato não encontrado!");
					}
					
				}catch(Exception erro) {
					JOptionPane.showMessageDialog(contentPane, "Erro ao trazer o contato - " + erro);
				}
			}
		});
		btnTrazerContato.setBounds(116, 227, 118, 23);
		contentPane.add(btnTrazerContato);
	}
}
