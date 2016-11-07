package br.org.ergildo.utilfaces.util;

import org.apache.commons.codec.binary.Base64;

import br.org.ergildo.utilfaces.core.model.FormatoImagem;



/**
 * Classe utilitária reponsável pelo o tratamento de imagem.
 * 
 * @author Ergildo C. Dias
 * 
 */
public final class ImagemUtil {

	/**
	 * Converte um array de byte para String, de forma que seja interpretado
	 * como imagem pelo graphicImage do JSF.
	 * 
	 * A String de retorno será formatada da seguinte forma: <br>
	 * <br>
	 * data: [ contentType ] ;base64, [ imagem encode base64 ] <br>
	 * <br>
	 * Base64 é um método para codificação de dados para transferência na
	 * Internet . É utilizado frequentemente para transmitir dados binários por
	 * meios de transmissão que lidam apenas com texto.
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
