package br.com.sura.dto;

import java.util.List;

import br.com.sura.model.Papel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UsuariorResponseDTO {

  @ApiModelProperty(position = 0)
  private Integer id;
  
  @ApiModelProperty(position = 1)
  private String usuario;
  
  @ApiModelProperty(position = 2)
  private String email;
  
  @ApiModelProperty(position = 3)
  List<Papel> papeis;

}