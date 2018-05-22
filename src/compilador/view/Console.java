package compilador.view;

import compilador.mensagem.Mensagem;

public class Console extends javax.swing.JPanel {

    public Console(Mensagem mensagem) {
        initComponents();
        setMensagem(mensagem);
    }

    public void setMensagem(Mensagem mensagem) {
        painelDeTexto.setForeground(mensagem.cor());
        painelDeTexto.setText(mensagem.conteudo());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        // Variables declaration - do not modify//GEN-BEGIN:variables
        javax.swing.JToolBar barraDeFerramentas = new javax.swing.JToolBar();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
        painelDeTexto = new javax.swing.JTextPane();

        barraDeFerramentas.setRollover(true);

        jLabel1.setText("Output");
        barraDeFerramentas.add(jLabel1);

        jScrollPane2.setViewportView(painelDeTexto);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barraDeFerramentas, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(barraDeFerramentas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    private javax.swing.JTextPane painelDeTexto;
    // End of variables declaration//GEN-END:variables
}
