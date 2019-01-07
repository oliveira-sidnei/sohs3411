package br.com.ins.control;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.caelum.stella.boleto.Banco;
import br.com.caelum.stella.boleto.Beneficiario;
import br.com.caelum.stella.boleto.Boleto;
import br.com.caelum.stella.boleto.Datas;
import br.com.caelum.stella.boleto.Endereco;
import br.com.caelum.stella.boleto.Pagador;
import br.com.caelum.stella.boleto.bancos.BancoDoBrasil;
import br.com.caelum.stella.boleto.transformer.GeradorDeBoleto;
import br.com.ins.core.Aluguel;
import br.com.ins.core.Despesa;
import br.com.ins.core.ItemAluguel;
import br.com.ins.core.Loja;
import br.com.ins.core.Lojista;
import br.com.ins.dao.AluguelDAO;

@Stateless
public class ControladorAluguel implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	AluguelDAO daoAluguel;

	public List<Despesa> retornaTodasDespesas() {
		return daoAluguel.listaDespesas();

	}

	public void insereAluguel(Aluguel aluguelSelecionado) {
		daoAluguel.insereAluguel(aluguelSelecionado);
	}

	public void insereItensAluguel(List<ItemAluguel> listaItensAluguel, Aluguel aluguelSelecionado) {
		daoAluguel.insereItensAluguel(listaItensAluguel, aluguelSelecionado);
	}

	public List<Aluguel> retornaTodosAlugueis() {
		List<Aluguel> listaAlugueis = daoAluguel.retornaTodosAlugueis();
		return listaAlugueis;
	}

	public Aluguel buscaAluguel(Integer idAluguel) {
		Aluguel aluguelSelecionado = daoAluguel.buscaAluguel(idAluguel);
		return aluguelSelecionado;
	}

	public List<ItemAluguel> listaItensAluguel(Aluguel aluguelSelecionado) {
		List<ItemAluguel> listaItensAluguel = daoAluguel.listaItensAluguel(aluguelSelecionado);
		return listaItensAluguel;
	}

	public void atualizaItensAluguel(List<ItemAluguel> listaItensAluguel) {
		daoAluguel.atualizaItensAluguel(listaItensAluguel);
	}

	public StreamedContent gereBoleto(Aluguel aluguelSelecionado) {

		Date dataAtual = new Date();
		Calendar dataGeracao = Calendar.getInstance();
		Calendar dataVencimento = Calendar.getInstance();

		dataGeracao.setTime(dataAtual);
		dataVencimento.setTime(aluguelSelecionado.getDataVencimento());

		Datas datas = Datas.novasDatas().comDocumento(dataGeracao).comProcessamento(dataGeracao)
				.comVencimento(dataVencimento);

		Endereco enderecoBeneficiario = Endereco.novoEndereco().comLogradouro("Av das Empresas, 555")
				.comBairro("Bairro Grande").comCep("01234-555").comCidade("São Paulo").comUf("SP");

		// Quem emite o boleto
		Beneficiario beneficiario = Beneficiario.novoBeneficiario().comNomeBeneficiario("Fulano de Tal")
				.comAgencia("1824").comDigitoAgencia("4").comCodigoBeneficiario("76000")
				.comDigitoCodigoBeneficiario("5").comNumeroConvenio("1207113").comCarteira("18")
				.comEndereco(enderecoBeneficiario).comNossoNumero("9000206");

		Endereco enderecoPagador = Endereco.novoEndereco().comLogradouro("Av dos testes, 111 apto 333")
				.comBairro("Bairro Teste").comCep("01234-111").comCidade("São Paulo").comUf("SP");

		// Quem paga o boleto
		Pagador pagador = Pagador.novoPagador().comNome(aluguelSelecionado.getLoja().getLojista().getNome())
				.comDocumento(aluguelSelecionado.getLoja().getLojista().getCpf()).comEndereco(enderecoPagador);

		Banco banco = new BancoDoBrasil();

		Boleto boleto = Boleto.novoBoleto().comBanco(banco).comDatas(datas).comBeneficiario(beneficiario)
				.comPagador(pagador).comValorBoleto(aluguelSelecionado.getValorTotal().toString())
				.comNumeroDoDocumento("1234")
				.comInstrucoes("instrucao 1", "instrucao 2", "instrucao 3", "instrucao 4", "instrucao 5")
				.comLocaisDePagamento("local 1", "local 2");

		GeradorDeBoleto gerador = new GeradorDeBoleto(boleto);

		// Para gerar um boleto em PDF
		// gerador.geraPDF(aluguelSelecionado.getLoja().getCnpj() + ".pdf");

		// Para gerar um boleto em PNG
		// gerador.geraPNG(aluguelSelecionado.getLoja().getCnpj() + ".png");

		// Para gerar um array de bytes a partir de um PDF
		byte[] bPDF = gerador.geraPDF();

		// Para gerar um array de bytes a partir de um PNG
//		byte[] bPNG = gerador.geraPNG();

		StreamedContent content = new DefaultStreamedContent(new ByteArrayInputStream(bPDF), "application/x-download",
				"boleto " + aluguelSelecionado.getLoja().getCnpj() + ".pdf");

		// aluguelSelecionado.setDocumento(bPDF);
		// daoAluguel.insereDocumento(aluguelSelecionado);
		return content;
	}

	// public ByteArrayInputStream selecionaDocumento(Integer i) {
	// documentoSelecionado = daoDocumento.selecionaDocumento(i);
	//
	// ByteArrayInputStream inputStream = new
	// ByteArrayInputStream(documentoSelecionado.getArquivo());
	// return inputStream;
	//
	// }

	public List<Aluguel> buscaPorLojista(Lojista lojistaSelecionado) {

		List<Aluguel> listaAlugueis = daoAluguel.buscaAluguelPorLojista(lojistaSelecionado);

		return listaAlugueis;
	}

	public void excluiAluguel(Aluguel aluguelSelecionado) {
		daoAluguel.excluiAluguel(aluguelSelecionado);
	}

	public ByteArrayInputStream selecionaDocumento(Aluguel aluguelDownload) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(aluguelDownload.getDocumento());
		return inputStream;
	}

	public List<Aluguel> buscaAluguelPorLoja(Loja loja) {
		List<Aluguel> alugueis = daoAluguel.buscaAluguelPorLoja(loja);
		return alugueis;
	}

	public List<Aluguel> lojasInadimplentes(Date dataInicio, Date dataFim) {

		List<Aluguel> alugueisNaoPagos = daoAluguel.lojasInadimplentes(dataInicio, dataFim);
		return alugueisNaoPagos;

	}

	public List<Aluguel> listaAlugueisPendentes() {
		List<Aluguel> listaAlugueis = daoAluguel.listaAlugueisPendentes();
		return listaAlugueis;
	}

	public void atualizaAluguel(Aluguel aluguelSelecionado) {
		daoAluguel.atualizaAluguel(aluguelSelecionado);
		
	}

}
