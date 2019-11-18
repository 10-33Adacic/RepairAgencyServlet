package ua.training.model.entity;


public class Request {

    private Long id;

    private String request;
    private String status;
    private Long price;
    private String reason;
    private String creator;
    private User master;
   // private Long requestNumber;

    public Request(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public User getMaster() {
        return master;
    }

    public void setMaster(User master) {
        this.master = master;
    }

//    public Long getRequestNumber() {
//        return requestNumber;
//    }
//
//    public void setRequestNumber(Long requestNumber) {
//        this.requestNumber = requestNumber;
//    }

    public static Builder builder() {
        return new Request().new Builder();
    }

    public class Builder {
        private Builder() {
        }

        public Builder id(Long id) {
            Request.this.id = id;
            return this;
        }

        public Builder request(String request) {
            Request.this.request = request;
            return this;
        }

        public Builder status(String status) {
            Request.this.status = status;
            return this;
        }

        public Builder price(Long price) {
            Request.this.price = price;
            return this;
        }

        public Builder reason(String  reason) {
            Request.this.reason = reason;
            return this;
        }
        public Builder creator(String creator) {
            Request.this.creator = creator;
            return this;
        }
        public Builder master(User master) {
            Request.this.master = master;
            return this;
        }
//        public Builder requestNumber(Long number){
//            Request.this.requestNumber=number;
//            return this;
//        }
        public Request build() {
            return Request.this;
        }
    }
}
