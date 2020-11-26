/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uaemtex.ico.erp.pedido.model;

import mx.uaemtex.ico.erp.producto.model.Producto;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Tmsanchez
 */
@Entity
@Table(name = "productos_pedido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductosPedido.findAll", query = "SELECT p FROM ProductosPedido p")})
public class ProductosPedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproductos_pedido")
    private Integer idproductosPedido;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private BigDecimal cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio")
    private BigDecimal precio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "importe")
    private BigDecimal importe;
    @JoinColumn(name = "idpedido", referencedColumnName = "idpedido")
    @ManyToOne(optional = false)
    private Pedido idpedido;
    @JoinColumn(name = "idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto idproducto;

    public ProductosPedido() {
    }

    public ProductosPedido(Integer idproductosPedido) {
        this.idproductosPedido = idproductosPedido;
    }

    public ProductosPedido(Integer idproductosPedido, BigDecimal cantidad, BigDecimal precio, BigDecimal importe) {
        this.idproductosPedido = idproductosPedido;
        this.cantidad = cantidad;
        this.precio = precio;
        this.importe = importe;
    }

    public Integer getIdproductosPedido() {
        return idproductosPedido;
    }

    public void setIdproductosPedido(Integer idproductosPedido) {
        this.idproductosPedido = idproductosPedido;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public Pedido getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Pedido idpedido) {
        this.idpedido = idpedido;
    }

    public Producto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Producto idproducto) {
        this.idproducto = idproducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproductosPedido != null ? idproductosPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductosPedido)) {
            return false;
        }
        ProductosPedido other = (ProductosPedido) object;
        if ((this.idproductosPedido == null && other.idproductosPedido != null) || (this.idproductosPedido != null && !this.idproductosPedido.equals(other.idproductosPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.uaemtex.ico.model.ProductosPedido[ idproductosPedido=" + idproductosPedido + " ]";
    }
    
}
