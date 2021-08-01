package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Reaction;

    /**
     * データのDTOモデル⇔Viewモデルの変換を行うクラス
     *
     */

    public class ReactionConverter {
        /**
         * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
         * @param rv ReportViewのインスタンス
         * @return Reportのインスタンス
         */
        public static Reaction toModel(ReactionView ractv) {
            return new Reaction(
                    ractv.getId(),
                    EmployeeConverter.toModel(ractv.getEmployee()),
                    ReportConverter.toModel(ractv.getReport()),
                    ractv.getReactionType(),
                    ractv.getCreatedAt());
        }

        /**
         * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
         * @param r Reportのインスタンス
         * @return ReportViewのインスタンス
         */
        public static ReactionView toView(Reaction ract) {

            if (ract == null) {
                return null;
            }

            return new ReactionView(
                    ract.getId(),
                    EmployeeConverter.toView(ract.getEmployee()),
                    ReportConverter.toView(ract.getReport()),
                    ract.getReactionType(),
                    ract.getCreatedAt());
        }

        /**
         * DTOモデルのリストからViewモデルのリストを作成する
         * @param list DTOモデルのリスト
         * @return Viewモデルのリスト
         */
        public static List<ReactionView> toViewList(List<Reaction> list) {
            List<ReactionView> evs = new ArrayList<>();

            for (Reaction ract : list) {
                evs.add(toView(ract));
            }

            return evs;
        }

        /**
         * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
         * @param r DTOモデル(コピー先)
         * @param rv Viewモデル(コピー元)
         */
        public static void copyViewToModel(Reaction ract, ReactionView ractv) {
            ract.setId(ract.getId());
            ract.setEmployee(EmployeeConverter.toModel(ractv.getEmployee()));
            ract.setReport(ReportConverter.toModel(ractv.getReport()));
            ract.setReactionType(ractv.getReactionType());
            ract.setCreatedAt(ractv.getCreatedAt());
        }

        /**
         * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
         * @param r DTOモデル(コピー元)
         * @param rv Viewモデル(コピー先)
         */
        public static void copyModelToView(Reaction ract, ReactionView ractv) {
            ractv.setId(ract.getId());
            ractv.setEmployee(EmployeeConverter.toView(ract.getEmployee()));
            ractv.setReport(ReportConverter.toView(ract.getReport()));
            ractv.setReactionType(ract.getReactionType());
            ractv.setCreatedAt(ract.getCreatedAt());
        }

    }

