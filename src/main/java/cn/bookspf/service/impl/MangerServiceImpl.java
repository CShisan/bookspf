package cn.bookspf.service.impl;

import cn.bookspf.mapper.*;
import cn.bookspf.model.Dao.*;
import cn.bookspf.model.Dto.*;
import cn.bookspf.model.RO.*;
import cn.bookspf.service.ManagerService;
import cn.bookspf.utils.Generator;
import cn.bookspf.utils.Operator;
import cn.bookspf.utils.Validator;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static cn.bookspf.constant.RoleConstant.MANAGER;

/**
 * @author Administrator
 */
@Service
public class MangerServiceImpl implements ManagerService {
    HttpSession httpSession;
    Validator validator;
    Operator operator;
    UserMapper userMapper;
    BookMapper bookMapper;
    SortMapper sortMapper;
    OrderMapper orderMapper;
    SaleMapper saleMapper;
    StockMapper stockMapper;
    PurchaseMapper purchaseMapper;

    @Autowired
    public MangerServiceImpl(HttpSession httpSession, UserMapper userMapper, BookMapper bookMapper,
                             SortMapper sortMapper, OrderMapper orderMapper, SaleMapper saleMapper,
                             StockMapper stockMapper, PurchaseMapper purchaseMapper) {
        this.httpSession=httpSession;
        this.validator=new Validator(httpSession);
        this.operator=new Operator();
        this.userMapper=userMapper;
        this.bookMapper=bookMapper;
        this.sortMapper=sortMapper;
        this.orderMapper=orderMapper;
        this.saleMapper=saleMapper;
        this.stockMapper=stockMapper;
        this.purchaseMapper=purchaseMapper;
    }

    @Override
    public Response manager() {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        return new Response(true,"success");
    }

    @Override
    public Response getBookList() {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        return new BookResponse(bookMapper.getBooks());
    }

    @Override
    public Response addBook(MultipartFile file, Integer bid, String bookname, Integer sortid, String author, String description, Double bookprice, Integer added) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        if(file==null) {
            return new Response(false,"上传失败");
        }
        if (bookMapper.findBid(bid)!=null) {
            return new Response(false,"图书编号已存在");
        }
        try{
            if(!operator.uploadBookimg(file,bid)) {
                return new Response(false,"上传图片失败");
            }
        }catch (IOException e){}
        BookDTO request =new BookDTO(bid,bookname,0,sortid,author,description,bookprice, added,0);
        bookMapper.addBook(request);
        return new Response(true,"添加成功");
    }

    @Override
    public Response alterBook(Boolean isUpImg, MultipartFile file, Integer bid, String bookname, Integer sortid, String author, String description, Double bookprice, Integer added) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        if(isUpImg){
            if(file==null) {
                return new Response(false,"上传图片失败");
            }
            try{
                if(!operator.uploadBookimg(file,bid)) {
                    return new Response(false,"上传图片失败");
                }
            }catch (IOException e){ return new Response(false,"上传图片失败");}
        }
        Book book = bookMapper.getBook(bid);
        BookDTO request =new BookDTO(bid,bookname,book.getHot(),sortid,author,description,bookprice, added,book.getNumber());
        bookMapper.alterBook(request);
        return new Response(true,"修改成功");
    }

    @Override
    public Response deleteBook(BookDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        Integer bid=request.getBid();
        try { operator.deleteBookimg(bid); } catch (IOException e){ return new Response(true,"删除失败"); }
        bookMapper.deleteBook(bid);
        return new Response(true,"删除成功");
    }

    @Override
    public Response getSortList() {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        return new SortResponse(sortMapper.getSorts());
    }

    @Override
    public Response addSort(SortDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        if(sortMapper.findSortid(request.getSortid())!=null) {
            return new Response(false,"分类ID重复");
        }
        if(sortMapper.findSortname(request.getSortname())!=null) {
            return new Response(false,"分类名字重复");
        }
        sortMapper.addSort(request);
        return new Response(true,"添加成功");
    }

    @Override
    public Response alterSort(SortDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        if(sortMapper.findSortname(request.getSortname())!=null) {
            return new Response(false,"分类名字重复");
        }
        sortMapper.alterSort(request);
        return new Response(true,"修改成功");
    }

    @Override
    public Response deleteSort(SortDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        sortMapper.deleteSort(request.getSortid());
        return new Response(true,"删除成功");
    }

    @Override
    public Response getOrderListOfAdmin(String request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        OrderResponse orderResponse=new OrderResponse();
        JSONObject Obj=JSONObject.parseObject(request);
        int index = Obj.getInteger("index");
        if(index==0){
            ArrayList<Order> order = orderMapper.getOrders();
            ArrayList<Double> price = orderMapper.getOrderPrice();
            orderResponse.setOrders(operator.getOrders(order,price));
        }else if(index==1){
            long orderid=Obj.getLong("str");
            ArrayList<Order> order = orderMapper.getOrderOfOrderid(orderid);
            ArrayList<Double> price = orderMapper.getOrderPriceOfOrderid(orderid);
            orderResponse.setOrders(operator.getOrders(order,price));
        }else if(index==2){
            Integer uid=Obj.getInteger("str");
            ArrayList<Order> order = orderMapper.getOrderOfUid(uid);
            ArrayList<Double> price = orderMapper.getOrderPriceOfUid(uid);
            orderResponse.setOrders(operator.getOrders(order,price));
        }else if(index==3){
            String createtime=Obj.getString("str");
            createtime=createtime.replace("T"," ");
            ArrayList<Order> order = orderMapper.getOrderOfCreatetime(createtime);
            ArrayList<Double> price = orderMapper.getOrderPriceOfCreatetime(createtime);
            orderResponse.setOrders(operator.getOrders(order,price));
        }
        return orderResponse;
    }

    @Override
    public Response checkOrderOfAdmin(OrderDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        Long orderid=request.getOrderid();
        ArrayList<Order> orders = orderMapper.getOrderinfoOfOrderid(orderid);
        ArrayList<Integer> bids =  orderMapper.getBidsOfOrderid(orderid);
        for(int i=0;i<orders.size();i++){
            String bookname=bookMapper.getBooknameOfAdmin(bids.get(i));
            orders.get(i).setBookname(bookname);
        }
        return new OrderResponse(orders);
    }

    @Override
    public Response getSaleList(String request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        JSONObject Obj=JSONObject.parseObject(request);
        int index = Obj.getInteger("index");
        ArrayList<Sale> sales=null;
        if(index==0){
            sales=saleMapper.getSalesinfo();
        }else if(index==1){
            long saleid=Obj.getLong("str");
            sales=saleMapper.getSalesinfoOfSaleid(saleid);
        }else if(index==2){
            String isbn=Obj.getString("str");
            sales=saleMapper.getSalesinfoOfISBN(isbn);
        }else if(index==3){
            String saletime=Obj.getString("str");
            saletime=saletime.replace("T"," ");
            sales=saleMapper.getSalesinfoOfSaletime(saletime);
        }
        for (int i=0;i<sales.size();i++){
            String bookname = bookMapper.getBooknameOfAdmin(sales.get(i).getBid());
            sales.get(i).setBookname(bookname);
        }
        return new SaleResponse(sales);
    }

    @Override
    public Response checkSale(SaleDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        Long saleid = request.getSaleid();
        ArrayList<Sale> sales = saleMapper.getSaleOfSaleid(saleid);
        for (int i=0;i<sales.size();i++){
            String bookname = bookMapper.getBooknameOfAdmin(sales.get(i).getBid());
            sales.get(i).setBookname(bookname);
        }
        return new SaleResponse(sales);
    }

    @Override
    public Response getPurchaseList(String request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        PurchaseResponse purchaseResponse=new PurchaseResponse();
        JSONObject Obj=JSONObject.parseObject(request);
        int index = Obj.getInteger("index");
        if(index==0){
            purchaseResponse.setPurchases(purchaseMapper.getPurchases());
        }else if(index==1){
            long stockid=Obj.getLong("str");
            purchaseResponse.setPurchases(purchaseMapper.getPurchaseOfPurchaseid(stockid));
        }else if(index==2){
            String purchasetime=Obj.getString("str");
            purchasetime=purchasetime.replace("T"," ");
            purchaseResponse.setPurchases(purchaseMapper.getPurchaseOfPurchasetime(purchasetime));
        }
        return purchaseResponse;
    }

    @Override
    public Response checkPurchase(PurchaseDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        Long purchaseid = request.getPurchaseid();
        ArrayList<Purchase> purchases = purchaseMapper.getPurchasesinfo(purchaseid);
        ArrayList<Integer> bids = purchaseMapper.getBidsOfPurchase(purchaseid);
        for(int i=0;i<purchases.size();i++){
            String bookname=bookMapper.getBooknameOfAdmin(bids.get(i));
            purchases.get(i).setBookname(bookname);
        }
        return new PurchaseResponse(purchases);
    }

    @Transactional
    @Override
    public Response addPurchase(PurchaseDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        UserDTO user = (UserDTO)httpSession.getAttribute("user");
        Integer uid = user.getUid();
        request.setOperator(uid);
        request.setPurchasetime(request.getPurchasetime().replace("T"," "));
        if(purchaseMapper.findPurchaseid(request.getPurchaseid()).size()!=0) {
            return new Response(false,"进货ID重复");
        }
        if(purchaseMapper.findIsbn(request.getIsbn())!=null) {
            return new Response(false,"ISBN重复");
        }
        Integer number=bookMapper.getBookNumberOfAdmin(request.getBid());
        if(number==null) {
            return new Response(false,"请先添加对应的图书,再进行操作");
        }

        //修改书本数量
        bookMapper.updateBookNumberOfAdmin(request.getBid(),number+1);

        //插入进货记录
        purchaseMapper.insertPurchase(request);

        //插入库存记录
        Long stockid = Generator.generateId();
        String time = Generator.generateTime();
        if(stockMapper.getStockinfoOfStockid(stockid).size()!=0) {
            stockid+=123;
        }
        stockMapper.insertComeStock(stockid,request.getBid(),request.getIsbn(),time);

        return new Response(true,"添加成功");
    }

    @Override
    public Response getStockList(String request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        StockResponse stockResponse=new StockResponse();
        JSONObject Obj=JSONObject.parseObject(request);
        int index = Obj.getInteger("index");
        if(index==0){
            stockResponse.setStocks(stockMapper.getStocks());
        }else if(index==1){
            long stockid=Obj.getLong("str");
            stockResponse.setStocks(stockMapper.getStockOfStockid(stockid));
        }else if(index==2){
            String stocktime=Obj.getString("str");
            stocktime=stocktime.replace("T"," ");
            stockResponse.setStocks(stockMapper.getStockOfStocktime(stocktime));
        }
        return stockResponse;
    }

    @Override
    public Response checkStock(StockDTO request) {
        Response loginStatus = validator.isIdentity(userMapper,MANAGER);
        if(!loginStatus.isStatus()){
            return loginStatus;
        }
        Long stockid = request.getStockid();
        ArrayList<Stock> stocks = stockMapper.getStockinfoOfStockid(stockid);
        ArrayList<Integer> bids = stockMapper.getBidsOfStockid(stockid);
        for(int i=0;i<stocks.size();i++){
            String bookname=bookMapper.getBooknameOfAdmin(bids.get(i));
            stocks.get(i).setBookname(bookname);
        }
        return new StockResponse(stocks);
    }
}
