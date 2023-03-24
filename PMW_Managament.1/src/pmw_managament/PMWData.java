/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pmw_managament;

/**
 *
 * @author PC
 */
public class PMWData {

    private int ID;
    private String Date;
    private String cause;
    private Long money;

    /**
     *
     */
    public PMWData() {
    }

    /**
     *
     * @param ID
     * @param Date
     * @param cause
     * @param money
     */
    public PMWData(int ID, String Date, String cause, Long money) {
        this.ID = ID;
        this.Date = Date;
        this.cause = cause;
        this.money = money;
    }

    /**
     *
     * @return
     */
    public int getID() {
        return ID;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        return Date;
    }

    /**
     *
     * @return
     */
    public String getCause() {
        return cause;
    }

    /**
     *
     * @return
     */
    public Long getMoney() {
        return money;
    }

    /**
     *
     * @param ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     *
     * @param Date
     */
    public void setDate(String Date) {
        this.Date = Date;
    }

    /**
     *
     * @param cause
     */
    public void setCause(String cause) {
        this.cause = cause;
    }

    /**
     *
     * @param money
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ID = " + ID + ", Date= " + Date + ", cause =" + cause + ", money=" + money;
    }

}
