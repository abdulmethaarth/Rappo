package com.affinity.rappo.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Machines extends BaseResponse{

    @SerializedName("result")
    private ArrayList<MachineList> customerRuslts;

    public Machines(){ }

    public ArrayList<MachineList> getCustomerRuslts() {
        return customerRuslts;
    }

    public class MachineList {
        @SerializedName("machineID")
        @Expose
        private String machineID;

        @SerializedName("machineName")
        @Expose
        private String machineName;

        public String getMachineID() {
            return machineID;
        }

        public void setMachineID(String machineID) {
            this.machineID = machineID;
        }

        public String getMachineName() {
            return machineName;
        }

        public void setMachineName(String machineName) {
            this.machineName = machineName;
        }
    }
}
