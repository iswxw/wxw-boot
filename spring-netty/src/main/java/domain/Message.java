package domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: wxw
 * @create: 2020-08-11-22:49
 */
@Data
@AllArgsConstructor
public class Message implements Serializable {
    private String content;
}
