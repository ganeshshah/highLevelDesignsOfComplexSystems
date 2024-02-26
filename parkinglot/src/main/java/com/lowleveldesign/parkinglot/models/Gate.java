package com.lowleveldesign.parkinglot.models;

public abstract class Gate {

    private Long gateNumber;
    private Operator operator;
    private GateType gateType;
    private GateStatus gateStatus;

    public Long getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(Long gateNumber) {
        this.gateNumber = gateNumber;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public GateType getGateType() {
        return gateType;
    }

    public void setGateType(GateType gateType) {
        this.gateType = gateType;
    }

    public GateStatus getGateStatus() {
        return gateStatus;
    }

    public void setGateStatus(GateStatus gateStatus) {
        this.gateStatus = gateStatus;
    }
}
