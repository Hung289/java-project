/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bkap.dao;

import bkap.entity.InfrastructureRoom;

/**
 *
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public interface InfrastrctureRoomDao {
    public int insert(InfrastructureRoom infrastructureRoom);
    public int update(InfrastructureRoom infrastructureRoom);
    public int selectRecord(int roomId);
    public int delete(int roomId);
}
