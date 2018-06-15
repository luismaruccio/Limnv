package com.br.linmv.sisbanco.view;

import com.br.linmv.sisbanco.controller.Operacoes_Clientes;
import com.br.linmv.sisbanco.controller.Operacoes_Bancarias;
import com.br.linmv.sisbanco.controller.Operacoes_Contas;
import com.br.linmv.sisbanco.model.Cliente;
import com.br.linmv.sisbanco.model.Conta;
import com.br.linmv.sisbanco.model.Extrato;
import com.br.linmv.sisbanco.model.Listas;
import com.sun.xml.internal.ws.util.StringUtils;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;

public class fMain extends javax.swing.JFrame {

    public List<Cliente> Clientes;
    Listas l = new Listas();
    DefaultTableModel tmc;
    Operacoes_Clientes opCli;
    Cliente edit;
    Cliente Inativar;

    public List<Conta> Contas;
    DefaultTableModel tmct;
    Operacoes_Contas opContas;
    Conta operConta;
    Conta InativarConta;
    Cliente CliContas;
    int posicaoCliContas;

    Extrato extratos;

    //Controle do PopUpMenu
    JPopupMenu PopUpMenu = new JPopupMenu();
    JMenuItem MISacar = new JMenuItem();
    JMenuItem MISaldo = new JMenuItem();
    JMenuItem MIDepositar = new JMenuItem();
    JMenuItem MIExtrato = new JMenuItem();
    JMenuItem MITransferir = new JMenuItem();

    public fMain() {
        this.Clientes = new ArrayList();
        initComponents();
        opCli = new Operacoes_Clientes();
        opContas = new Operacoes_Contas();
    }

    private String GetNumConta(int CodCli, int CodCon) {
        return ((String.format("%03d", CodCli)) + "-" + (String.format("%04d", CodCon)));
    }

    private void retornaExtrato() {
        int slcRow = tblContas.getSelectedRow();

        int CodContaSelecionada = Integer.parseInt(tmct.getValueAt(slcRow, 0).toString());
        int Posicao = opContas.BuscarCod(Contas, CodContaSelecionada);

        operConta = opContas.GetConta(Contas, Posicao);

        extratos = opContas.getExtrato(operConta);
    }

    private void ControlePopUpMenu() {

        //Seta Nomes
        MISaldo.setText("Saldo");
        MISaldo.addActionListener(acaoMISaldo());
        MIDepositar.setText("Depositar");
        MIDepositar.addActionListener(acaoMIDepositar());
        MISacar.setText("Sacar");
        MISacar.addActionListener(acaoMISacar());
        MITransferir.setText("Transferir");
        MITransferir.addActionListener(acaoMITransferencia());
        MIExtrato.setText("Extrato");
        MIExtrato.addActionListener(acaoMIExtrato());

        //Adiciona no PopUpMenu
        PopUpMenu.add(MISaldo);
        PopUpMenu.add(MISacar);
        PopUpMenu.add(MIDepositar);
        PopUpMenu.add(MITransferir);
        PopUpMenu.add(MIExtrato);

    }

    private ActionListener acaoMISacar() {
        ActionListener al;
        al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = 0;
                    while (i < 3) {

                        JPasswordField password = new JPasswordField(10);
                        password.setEchoChar('*');
                        JLabel rotulo = new JLabel("Confirme sua senha:");
                        JPanel entUsuario = new JPanel();
                        entUsuario.add(rotulo);
                        entUsuario.add(password);

                        JOptionPane.showMessageDialog(null, entUsuario, "Acesso restrito", JOptionPane.PLAIN_MESSAGE);
                        String senha = password.getText();

                        if (!senha.equals(CliContas.getSenha())) {
                            i++;
                        } else {
                            break;
                        }
                    }
                    if (i < 3) {
                        fOperContas OperContas = new fOperContas();
                        OperContas.Oper = "Saque";
                        OperContas.NConta = GetNumConta(CliContas.getCodigo(), operConta.getCodigo());
                        OperContas.SaldoAtual = opContas.Consulta_Saldo(operConta);
                        OperContas.setCallback(new CallBack_OperacoesBancarias() {
                            @Override
                            public void operacaoEfetuadaCall(double vlr) {
                                opContas.Saque(operConta, vlr);
                                opContas.inserirLancamentos("Saque", vlr, opContas.Consulta_Saldo(operConta), extratos);
                            }
                        });
                        OperContas.show();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        };
        return al;
    }

    private ActionListener acaoMISaldo() {
        ActionListener al;
        al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = 0;
                    while (i < 3) {

                        JPasswordField password = new JPasswordField(10);
                        password.setEchoChar('*');
                        JLabel rotulo = new JLabel("Confirme sua senha:");
                        JPanel entUsuario = new JPanel();
                        entUsuario.add(rotulo);
                        entUsuario.add(password);

                        JOptionPane.showMessageDialog(null, entUsuario, "Acesso restrito", JOptionPane.PLAIN_MESSAGE);
                        String senha = password.getText();

                        if (!senha.equals(CliContas.getSenha())) {
                            i++;
                        } else {
                            break;
                        }
                    }
                    if (i < 3) {
                        JOptionPane.showMessageDialog(null, "Seu saldo atual \n \n R$ " + opContas.Consulta_Saldo(operConta), "Saldo...", JOptionPane.INFORMATION_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        };
        return al;
    }

    private ActionListener acaoMIDepositar() {
        ActionListener al;
        al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    fOperContas OperContas = new fOperContas();
                    OperContas.Oper = "Depósito";
                    OperContas.NConta = GetNumConta(CliContas.getCodigo(), operConta.getCodigo());
                    OperContas.SaldoAtual = opContas.Consulta_Saldo(operConta);
                    OperContas.setCallback(new CallBack_OperacoesBancarias() {
                        @Override
                        public void operacaoEfetuadaCall(double vlr) {
                            opContas.Depositar(operConta, vlr);
                            opContas.inserirLancamentos("Depósito", vlr, opContas.Consulta_Saldo(operConta), extratos);
                        }
                    });
                    OperContas.show();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        };
        return al;
    }

    private ActionListener acaoMITransferencia() {
        ActionListener al;
        al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int i = 0;
                    while (i < 3) {

                        JPasswordField password = new JPasswordField(10);
                        password.setEchoChar('*');
                        JLabel rotulo = new JLabel("Confirme sua senha:");
                        JPanel entUsuario = new JPanel();
                        entUsuario.add(rotulo);
                        entUsuario.add(password);

                        JOptionPane.showMessageDialog(null, entUsuario, "Acesso restrito", JOptionPane.PLAIN_MESSAGE);
                        String senha = password.getText();

                        if (!senha.equals(CliContas.getSenha())) {
                            i++;
                        } else {
                            break;
                        }
                    }
                    if (i < 3) {

                        fTransferencia fTransf = new fTransferencia();
                        fTransf.Clientes = Clientes;
                        fTransf.opCli = opCli;
                        fTransf.opContas = opContas;
                        fTransf.saldo_atual = opContas.Consulta_Saldo(operConta);
                        fTransf.setCallBack(new CallBack_Transferencia() {
                            @Override
                            public void operacaoEfetuadaCall(double vlr, Conta Destino) {
                                opContas.Transferir(vlr, operConta, Destino);
                                opContas.inserirLancamentos("Tranferência", vlr, opContas.Consulta_Saldo(operConta), extratos);

                            }
                        });
                        fTransf.show();
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        };
        return al;
    }

    private ActionListener acaoMIExtrato() {
        ActionListener al;
        al = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (operConta != null) {
                        fExtratos ext = new fExtratos();
                        ext.ext = extratos;
                        ext.show();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        };
        return al;
    }

    private void PopularTblClientes() {
        tmc = (DefaultTableModel) tblClientes.getModel();

        while (tmc.getRowCount() > 0) {
            tmc.removeRow(0);
        }

        for (Cliente c : Clientes) {
            if (!c.isInativo()) {
                tmc.addRow(new Object[]{c.getCodigo(), c.getNome(), c.getCpf()});
            }
        }

    }

    private void PopularTblContas() {
        tmct = (DefaultTableModel) tblContas.getModel();

        while (tmct.getRowCount() > 0) {
            tmct.removeRow(0);
        }

        for (Conta ct : Contas) {
            if (!ct.isInativo()) {
                tmct.addRow(new Object[]{ct.getCodigo(), ct.getTipo()});
            }
        }

        tblContas.addMouseListener(
                new java.awt.event.MouseAdapter() {
            //Importe a classe java.awt.event.MouseEvent
            public void mouseClicked(MouseEvent e) {
                // Se o botão direito do mouse foi pressionado
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // Exibe o popup menu na posição do mouse.
                    PopUpMenu.show(tblContas, e.getX(), e.getY());
                }
            }
        }
        );
    }

    public void AtivaContas() {
        CliContas = RetornaCliente();

        Contas = CliContas.getContas();
        PopularTblContas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        pnlClientes = new javax.swing.JPanel();
        btnBuscarCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        btnInativar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        ftxtBusca = new javax.swing.JFormattedTextField();
        pnlContas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblContas = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        btnInativarConta = new javax.swing.JButton();
        btnNovaConta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTitulo.setText("Linmv");

        pnlClientes.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes"));

        btnBuscarCliente.setText("Buscar");
        btnBuscarCliente.setToolTipText("");
        btnBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteActionPerformed(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cod.", "Nome", "CPF"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblClientes.getTableHeader().setReorderingAllowed(false);
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);
        if (tblClientes.getColumnModel().getColumnCount() > 0) {
            tblClientes.getColumnModel().getColumn(0).setResizable(false);
            tblClientes.getColumnModel().getColumn(0).setPreferredWidth(6);
            tblClientes.getColumnModel().getColumn(1).setResizable(false);
            tblClientes.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblClientes.getColumnModel().getColumn(2).setResizable(false);
            tblClientes.getColumnModel().getColumn(2).setPreferredWidth(40);
        }

        btnInativar.setText("Inativar");
        btnInativar.setEnabled(false);
        btnInativar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInativarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        try {
            ftxtBusca.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pnlClientesLayout = new javax.swing.GroupLayout(pnlClientes);
        pnlClientes.setLayout(pnlClientesLayout);
        pnlClientesLayout.setHorizontalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlClientesLayout.createSequentialGroup()
                        .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlClientesLayout.createSequentialGroup()
                                .addComponent(ftxtBusca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscarCliente)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlClientesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlClientesLayout.createSequentialGroup()
                                .addComponent(btnNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnInativar)))))
                .addContainerGap())
        );
        pnlClientesLayout.setVerticalGroup(
            pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlClientesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarCliente)
                    .addComponent(ftxtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInativar)
                    .addComponent(btnEditar)
                    .addComponent(btnNovo)))
        );

        pnlContas.setBorder(javax.swing.BorderFactory.createTitledBorder("Contas"));
        pnlContas.setPreferredSize(new java.awt.Dimension(339, 484));

        tblContas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nº Conta", "Tipo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblContas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblContasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblContas);
        if (tblContas.getColumnModel().getColumnCount() > 0) {
            tblContas.getColumnModel().getColumn(0).setResizable(false);
            tblContas.getColumnModel().getColumn(1).setResizable(false);
        }

        btnInativarConta.setText("Inativar");
        btnInativarConta.setEnabled(false);
        btnInativarConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInativarContaActionPerformed(evt);
            }
        });

        btnNovaConta.setText("Nova");
        btnNovaConta.setEnabled(false);
        btnNovaConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaContaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContasLayout = new javax.swing.GroupLayout(pnlContas);
        pnlContas.setLayout(pnlContasLayout);
        pnlContasLayout.setHorizontalGroup(
            pnlContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContasLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlContasLayout.createSequentialGroup()
                                .addComponent(btnNovaConta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnInativarConta))
                            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        pnlContasLayout.setVerticalGroup(
            pnlContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlContasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInativarConta)
                    .addComponent(btnNovaConta)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlContas, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTitulo)
                        .addGap(215, 620, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlContas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlClientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private Cliente RetornaCliente() {

        int slcRow = tblClientes.getSelectedRow();

        int CodCliSelecionado = Integer.parseInt(tmc.getValueAt(slcRow, 0).toString());
        int Posicao = opCli.BuscarCod(Clientes, CodCliSelecionado);

        posicaoCliContas = Posicao;

        return opCli.GetCliente(Clientes, Posicao);
    }

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        fCadCliente cadcliente = new fCadCliente();
        cadcliente.Clientes = Clientes;
        cadcliente.opCli = opCli;
        cadcliente.setCallback(new CallBack_Cliente() {
            @Override
            public void clienteCadastradoCall(Cliente c) {
                if (c != null) {
                    opCli.Inserir(c, Clientes);
                }
                PopularTblClientes();
            }

            @Override
            public void clienteEditadoCall(Cliente cliente) {

            }
        });
        cadcliente.show();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int slcRow = tblClientes.getSelectedRow();

        int CodCliSelecionado = Integer.parseInt(tmc.getValueAt(slcRow, 0).toString());
        int Posicao = opCli.BuscarCod(Clientes, CodCliSelecionado);

        edit = opCli.GetCliente(Clientes, Posicao);

        fCadCliente cadcliente = new fCadCliente();
        cadcliente.c = edit;
        cadcliente.setCallback(new CallBack_Cliente() {
            @Override
            public void clienteCadastradoCall(Cliente c) {
            }
            @Override
            public void clienteEditadoCall(Cliente c) {
                if (c != null) {
                    opCli.Editar(c, Clientes, Posicao);
                    PopularTblClientes();
                }
            }
        });
        cadcliente.show();

    }//GEN-LAST:event_btnEditarActionPerformed

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        if (tblClientes.getSelectedRowCount() > 0) {
            btnEditar.setEnabled(true);
            btnInativar.setEnabled(true);
            btnNovaConta.setEnabled(true);
            AtivaContas();
        } else {
            btnEditar.setEnabled(false);
            btnInativar.setEnabled(false);
            btnNovaConta.setEnabled(false);
        }
    }//GEN-LAST:event_tblClientesMouseClicked

    private void btnInativarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInativarActionPerformed
        int slcRow = tblClientes.getSelectedRow();

        int CodCliSelecionado = Integer.parseInt(tmc.getValueAt(slcRow, 0).toString());
        int Posicao = opCli.BuscarCod(Clientes, CodCliSelecionado);

        Inativar = opCli.GetCliente(Clientes, Posicao);

        int Resul = JOptionPane.showConfirmDialog(null, "Deseja inativar o cliente " + Inativar.getNome() + " ?", "Certeza...", JOptionPane.YES_NO_OPTION);

        if (Resul == JOptionPane.YES_OPTION) {
            opCli.Inativar(Inativar, Clientes, Posicao);
            PopularTblClientes();
        }
    }//GEN-LAST:event_btnInativarActionPerformed

    private void btnNovaContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaContaActionPerformed
        fCadConta cadconta = new fCadConta();
        cadconta.seq = Contas.size() + 1;
        cadconta.show();

        cadconta.setCallback(new CallBack_Conta() {
            @Override
            public void contaCadastradaCall(Conta ct) {
                if (ct != null) {
                    opContas.Inserir(ct, Contas);
                    opCli.SetContas(Contas, CliContas);
                }
                PopularTblContas();
            }

            @Override
            public void contaEditadaCall(Conta conta) {
            }
        });

        cadconta.show();
    }//GEN-LAST:event_btnNovaContaActionPerformed

    private void tblContasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblContasMouseClicked
        if (tblContas.getSelectedRowCount() > 0) {
            btnInativarConta.setEnabled(true);
            retornaExtrato();
        } else {
            btnInativarConta.setEnabled(false);
        }
    }//GEN-LAST:event_tblContasMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        ControlePopUpMenu();
    }//GEN-LAST:event_formWindowOpened

    private void btnBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteActionPerformed
        if ("   .   .   -  ".equals(ftxtBusca.getText())) {
            PopularTblClientes();
        } else {
            tmc = (DefaultTableModel) tblClientes.getModel();

            while (tmc.getRowCount() > 0) {
                tmc.removeRow(0);
            }

            for (Cliente c : Clientes) {
                if ((!c.isInativo()) && c.getCpf().equals(ftxtBusca.getText())) {
                    tmc.addRow(new Object[]{c.getCodigo(), c.getNome(), c.getCpf()});
                }
            }
        }
        ftxtBusca.setText("");
    }//GEN-LAST:event_btnBuscarClienteActionPerformed

    private void btnInativarContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInativarContaActionPerformed
        int slcRow = tblContas.getSelectedRow();

        int CodContaSelecionada = Integer.parseInt(tmct.getValueAt(slcRow, 0).toString());
        int Posicao = opContas.BuscarCod(Contas, CodContaSelecionada);

        InativarConta = opContas.GetConta(Contas, Posicao);
        
        int Resul = JOptionPane.showConfirmDialog(null, "Deseja inativar a conta " + GetNumConta(CliContas.getCodigo(), InativarConta.getCodigo()) + " ?", "Certeza...", JOptionPane.YES_NO_OPTION);

        if (Resul == JOptionPane.YES_OPTION) {
            opContas.Inativar(InativarConta, Contas, Posicao);
            PopularTblContas();
        }
    }//GEN-LAST:event_btnInativarContaActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscarCliente;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInativar;
    private javax.swing.JButton btnInativarConta;
    private javax.swing.JButton btnNovaConta;
    private javax.swing.JButton btnNovo;
    private javax.swing.JFormattedTextField ftxtBusca;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlClientes;
    private javax.swing.JPanel pnlContas;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTable tblContas;
    // End of variables declaration//GEN-END:variables
}
