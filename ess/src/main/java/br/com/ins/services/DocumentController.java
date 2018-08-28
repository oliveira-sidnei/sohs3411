package br.com.ins.services;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.ins.control.ControladorAluguel;
import br.com.ins.control.ControladorEvento;
import br.com.ins.control.ControladorMensagens;
import br.com.ins.control.ControladorTipoEvento;
import br.com.ins.core.Aluguel;
import br.com.ins.core.Evento;
import br.com.ins.core.Loja;
import br.com.ins.core.TipoEvento;

@ManagedBean
@SessionScoped
public class DocumentController implements Serializable {

	private static final long serialVersionUID = 1L;

	private StreamedContent content;
	@EJB
	ControladorAluguel controladorAluguel;
	@EJB
	ControladorTipoEvento controladorTipoEvento;
	@EJB
	ControladorEvento controladorEvento;
	@EJB
	ControladorMensagens controladorMensagens;

	public void geraPdfEventosProcurados(Date dataInicio, Date dataFim) {

			
			List<TipoEvento> listaTiposEvento = controladorTipoEvento.tipoEventoMaisProcurados(dataInicio, dataFim);

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			NumberFormat nf = NumberFormat.getCurrencyInstance();
			DecimalFormatSymbols dFS = ((DecimalFormat) nf).getDecimalFormatSymbols();
			dFS.setCurrencySymbol("");
			((DecimalFormat) nf).setDecimalFormatSymbols(dFS);

			Document document = new Document();

			try {
				PdfWriter.getInstance(document, out);

				document.open();

				document.add(new Paragraph(negrito("Tipos de evento mais procurados ")));
				document.add(new Paragraph());
				document.add(new Paragraph(negrito("Período: " + new SimpleDateFormat("dd/MM/yyyy").format(dataInicio)
						+ " à " + new SimpleDateFormat("dd/MM/yyyy").format(dataFim))));

				document.add(new Paragraph("                                                        "));
				PdfPTable table1col = new PdfPTable(2);
				PdfPTable table2col = new PdfPTable(2);

				table1col.setWidthPercentage(100);
				table1col.setWidths(new int[] { 50, 50 });
				table1col.addCell(negrito("Tipo de Evento"));
				table1col.addCell(negrito("Ocorrências no período"));
				document.add(table1col);
				document.add(new Paragraph("                                                        "));

				for (TipoEvento tipoEvento : listaTiposEvento) {
					table2col.setWidthPercentage(100);
					table2col.setWidths(new int[] { 50, 50 });
					table2col.getDefaultCell().setPaddingBottom(5);
					table2col.addCell(tipoEvento.getDescricao());
					table2col.addCell(tipoEvento.getOcorrencia().toString());

				}
				document.add(table2col);
				document.close();

				content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");
				listaTiposEvento = new ArrayList<TipoEvento>();
			} catch (Exception e) {
				e.printStackTrace();
			}
	
	}

	public void geraPdfAlugueis(Date dataInicio, Date dataFim) {
			
			List<Aluguel> listaAlugueis = new ArrayList<Aluguel>();

			listaAlugueis = controladorAluguel.lojasInadimplentes(dataInicio, dataFim);
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			NumberFormat nf = NumberFormat.getCurrencyInstance();
			DecimalFormatSymbols dFS = ((DecimalFormat) nf).getDecimalFormatSymbols();
			dFS.setCurrencySymbol("");
			((DecimalFormat) nf).setDecimalFormatSymbols(dFS);

			Document document = new Document();

			try {
				PdfWriter.getInstance(document, out);

				document.open();

				document.add(new Paragraph(negrito("Lojas inadimplentes")));
				document.add(new Paragraph());
				document.add(new Paragraph(negrito("Período: " + new SimpleDateFormat("dd/MM/yyyy").format(dataInicio)
						+ " à " + new SimpleDateFormat("dd/MM/yyyy").format(dataFim))));

				document.add(new Paragraph("                                                        "));
				PdfPTable table1col = new PdfPTable(5);
				PdfPTable table2col = new PdfPTable(5);

				table1col.setWidthPercentage(100);
				table1col.setWidths(new int[] { 20, 20, 20, 20, 20 });
				table1col.addCell(negrito("Loja"));
				table1col.addCell(negrito("CNPJ"));
				table1col.addCell(negrito("Data de vencimento"));
				table1col.addCell(negrito("Valor"));
				table1col.addCell(negrito("Status"));
				document.add(table1col);
				document.add(new Paragraph("                                                        "));

				for (Aluguel aluguel : listaAlugueis) {

					table2col.setWidthPercentage(100);
					table2col.setWidths(new int[] { 20, 20, 20, 20, 20 });
					table2col.getDefaultCell().setPaddingBottom(5);
					table2col.addCell(aluguel.getLoja().getNome());
					table2col.addCell(aluguel.getLoja().getCnpj());
					table2col.addCell(new SimpleDateFormat("dd/MM/yyyy").format(aluguel.getDataVencimento()));
					table2col.addCell("R$ " + nf.format(aluguel.getValorTotal()));
					table2col.addCell(aluguel.getStatus().getDescricao());

				}
				document.add(table2col);

				document.close();
				content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");
				listaAlugueis = new ArrayList<Aluguel>();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	public void geraPdfEventosPorLoja(Loja lojaSelecionada) {

		List<Evento> listaEventos = new ArrayList<Evento>();
		listaEventos = controladorEvento.eventosPorLoja(lojaSelecionada);
		System.out.println(listaEventos.size());
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		NumberFormat nf = NumberFormat.getCurrencyInstance();
		DecimalFormatSymbols dFS = ((DecimalFormat) nf).getDecimalFormatSymbols();
		dFS.setCurrencySymbol("");
		((DecimalFormat) nf).setDecimalFormatSymbols(dFS);

		Document document = new Document();

		try {
			PdfWriter.getInstance(document, out);

			document.open();

			document.add(new Paragraph(negrito("Eventos por Loja e seu valor")));
			document.add(new Paragraph());
			document.add(new Paragraph(negrito(lojaSelecionada.getNome())));

			document.add(new Paragraph("                                                        "));
			PdfPTable table1col = new PdfPTable(2);
			PdfPTable table2col = new PdfPTable(2);

			table1col.setWidthPercentage(100);
			table1col.setWidths(new int[] { 50, 50 });
			table1col.addCell(negrito("Evento"));
			table1col.addCell(negrito("Valor do ingresso"));
			document.add(table1col);
			document.add(new Paragraph("                                                        "));

			for (Evento evento : listaEventos) {

				table2col.setWidthPercentage(100);
				table2col.setWidths(new int[] { 50, 50 });
				table2col.getDefaultCell().setPaddingBottom(5);
				table2col.addCell(evento.getNome());
				table2col.addCell("R$" + nf.format(evento.getValorIngresso()));
			}

			document.add(table2col);
			document.close();

			content = new DefaultStreamedContent(new ByteArrayInputStream(out.toByteArray()), "application/pdf");
			listaEventos = new ArrayList<Evento>();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Phrase negrito(String frase) {
		Phrase phrase = new Phrase();
		phrase.add(new Chunk(frase, new Font(FontFamily.HELVETICA, 12, Font.BOLD)));
		return phrase;
	}

	public StreamedContent getContent() {
		return content;
	}

	public void setContent(StreamedContent content) {
		this.content = content;
	}

}
