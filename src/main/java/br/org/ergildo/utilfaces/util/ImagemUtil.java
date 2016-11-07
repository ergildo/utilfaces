package br.org.ergildo.utilfaces.util;

import org.apache.commons.codec.binary.Base64;

import br.org.ergildo.utilfaces.core.model.FormatoImagem;



/**
 * Classe utilit�ria repons�vel pelo o tratamento de imagem.
 * 
 * @author Ergildo C. Dias
 * 
 */
public final class ImagemUtil {

	/**
	 * Converte um array de byte para String, de forma que seja interpretado
	 * como imagem pelo graphicImage do JSF.
	 * 
	 * A String de retorno ser� formatada da seguinte forma: <br>
	 * <br>
	 * data: [ contentType ] ;base64, [ imagem encode base64 ] <br>
	 * <br>
	 * Base64 � um m�todo para codifica��o de dados para transfer�ncia na
	 * Internet . � utilizado frequentemente para transmitir dados bin�rios por
	 * meios de transmiss�o que lidam apenas com texto.
	 * 
	 * @param mimeType
	 * @param bytes
	 * @return
	 */
	public static String getStringImagem(FormatoImagem formatoImagem,
			final byte[] bytes) {

		String encodeImg = new String(Base64.encodeBase64(bytes));

		StringBuilder imagem = new StringBuilder();

		imagem.append("data:").append(formatoImagem.getContentType())
				.append(";base64,").append(encodeImg);

		return imagem.toString();
	}

}
