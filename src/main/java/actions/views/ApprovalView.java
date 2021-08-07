package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールドについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドに引数を持つ引数ありコンストラクタを自動生成する(Lombok)

public class ApprovalView {
    private Integer id;

    private ReportView report;

    //private Integer approvalFlag;

    private LocalDateTime createdAt;

    private LocalDateTime UpdatedAt;


}
