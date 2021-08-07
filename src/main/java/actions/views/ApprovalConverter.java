package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Approval;

public class ApprovalConverter {
    public static Approval toModel(ApprovalView apv) {
        return new Approval(
                apv.getId(),
                ReportConverter.toModel(apv.getReport()),
                //apv.getApprovalFlag(),
                apv.getCreatedAt(),
                apv.getUpdatedAt());
    }

    public static ApprovalView toView(Approval ap) {
        if (ap == null) {
            return null;
        }
        return new ApprovalView(
                ap.getId(),
                ReportConverter.toView(ap.getReport()),
                //ap.getApprovalFlag(),
                ap.getCreatedAt(),
                ap.getUpdatedAt());
    }

    public static List<ApprovalView> toViewList(List<Approval> list){
        List<ApprovalView> apvs = new ArrayList<>();

        for (Approval ap : list) {
            apvs.add(toView(ap));
        }

        return apvs;
    }

    public static void copyViewToModel(Approval ap,ApprovalView apv) {
        ap.setId(apv.getId());
        ap.setReport(ReportConverter.toModel(apv.getReport()));
        //ap.setApprovalFlag(apv.getApprovalFlag());
        ap.setCreatedAt(apv.getCreatedAt());
        ap.setUpdatedAt(apv.getUpdatedAt());
    }

    public static void copyModelToView(Approval ap,ApprovalView apv) {
        apv.setId(ap.getId());
        apv.setReport(ReportConverter.toView(ap.getReport()));
       //apv.setApprovalFlag(ap.getApprovalFlag());
        apv.setCreatedAt(ap.getCreatedAt());
        apv.setUpdatedAt(ap.getUpdatedAt());
    }
}
