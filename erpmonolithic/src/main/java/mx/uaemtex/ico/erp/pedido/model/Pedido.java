/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemtex.ico.erp.pedido.model;

import mx.uaemtex.ico.erp.cliente.model.Cliente;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import mx.uaemtex.ico.erp.producto.model.Producto;

/**
 *
 * @author Tmsanchez
 */
@Entity
@Table(name = "pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpedido")
    private Integer idpedido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Size(max = 255)
    @Column(name = "comentarios")
    private String comentarios;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente idcliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpedido")
    private List<ProductosPedido> productosPedidoList;

    public Pedido() {
        fecha = new Date();
        total = BigDecimal.ZERO;
        productosPedidoList = new ArrayList();
    }

    public Pedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Pedido(Integer idpedido, Date fecha, BigDecimal total) {
        this.idpedido = idpedido;
        this.fecha = fecha;
        this.total = total;
    }

    public Integer getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Integer idpedido) {
        this.idpedido = idpedido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Cliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Cliente idcliente) {
        this.idcliente = idcliente;
    }

    @XmlTransient
    public List<ProductosPedido> getProductosPedidoList() {
        return productosPedidoList;
    }

    public void setProductosPedidoList(List<ProductosPedido> productosPedidoList) {
        this.productosPedidoList = productosPedidoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpedido != null ? idpedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idpedido == null && other.idpedido != null) || (this.idpedido != null && !this.idpedido.equals(other.idpedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.uaemtex.ico.model.Pedido[ idpedido=" + idpedido + " ]";
    }
    
    public void agregaProducto(Producto producto, BigDecimal cantidad) {
       ProductosPedido productosPedido = new ProductosPedido();
       productosPedido.setIdpedido(this);
       productosPedido.setIdproducto(producto);
       productosPedido.setCantidad(cantidad);
       productosPedido.setPrecio(producto.getPrecio());
       productosPedido.setImporte(cantidad.multiply(producto.getPrecio()));
       
       productosPedidoList.add(productosPedido);
       
       calculaTotal();
    }
    
    public void calculaTotal() {
        total = BigDecimal.ZERO;
        productosPedidoList.forEach(productosPedido -> {
            total = total.add(productosPedido.getImporte());
        });
    }
    
}
