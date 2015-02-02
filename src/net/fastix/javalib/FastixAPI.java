package net.fastix.javalib;


import net.fastix.javalib.exceptions.FastixConnectionException;
import net.fastix.javalib.exceptions.RequestUnsuccessfulException;
import net.fastix.javalib.hold.*;

/**
 * Created by Neutro on 26/01/2015.
 */
public interface FastixAPI {
    /**
     * Method setts api key, if it wasnt provided yet
     *
     * @param apiKey {@link String}
     */
    public void setApiKey(String apiKey);

    /**
     * Method setts api key, if it wasnt provided yet
     *
     * @param apiKey {@link net.fastix.javalib.hold.ApiKey}
     */
    public void setApiKey(ApiKey apiKey);

    /**
     * Method returns user information
     *
     * @return {@link net.fastix.javalib.hold.Account}
     * @throws net.fastix.javalib.exceptions.FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public Account getAccount() throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method returns ApiKey by Email/Password
     *
     * @param email    User Email
     * @param password Password
     * @return {@link net.fastix.javalib.hold.ApiKey}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public ApiKey getApiKey(String email, String password) throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method returns ALL fastix active proxy nodes
     *
     * @return {@link net.fastix.javalib.hold.Nodes}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public Nodes getNodes() throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method retrives all prices share by share.
     *
     * @return {@link net.fastix.javalib.hold.Prices}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public Prices getPrices() throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method gets all infos about file on fileshare
     *
     * @param link fileshare link
     * @return {@link net.fastix.javalib.hold.File}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public File getLinkInfo(String link) throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Generates downloading link and mirrors (if exists),
     * IP restrictions and forsed ssl are disabled
     *
     * @param link fileshare link
     * @return {@link net.fastix.javalib.hold.DirectedFile}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public DirectedFile getDirectLink(String link) throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Generates downloading link and mirrors (if exists)
     *
     * @param link      fileshare link
     * @param forsedSSL if true - download link will use SSL connection only
     * @return {@link net.fastix.javalib.hold.DirectedFile}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public DirectedFile getDirectLink(String link, boolean forsedSSL) throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Generates downloading link and mirrors (if exists)
     * with IP restriction
     *
     * @param link fileshare link
     * @param ip   IPv4 address, without mask ex. 178.178.1.123
     * @return {@link net.fastix.javalib.hold.DirectedFile}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public DirectedFile getDirectLink(String link, String ip) throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Generates downloading link and mirrors (if exists)
     *
     * @param link      fileshare link
     * @param ip        IPv4 address, without mask ex. 178.178.1.123
     * @param forsedSSL if true - download link will use SSL connection only
     * @return {@link net.fastix.javalib.hold.DirectedFile}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public DirectedFile getDirectLink(String link, String ip, boolean forsedSSL) throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method returns all generated files in {@param from} - {@param to} period
     *
     * @param from timestamp selection FROM
     * @param to   timestamp selection TO
     * @return {@link net.fastix.javalib.hold.HistoryFiles}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public HistoryFiles getHistoryFiles(int from, int to) throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method returns details about single downloaded file
     *
     * @param fileID downloaded file id
     * @return {@link net.fastix.javalib.hold.HistoryFile}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public HistoryFile getHistoryFile(String fileID) throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method disables file proxing
     *
     * @param fileID downloaded file id
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public void setFileDisabled(String fileID) throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method asks Fastix to refresh statistic data.
     * !!Warning!! this method can be called only once every 10 min. otherwise exception raised
     *
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public void refreshStatistic() throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method retrieves general service status share by share
     *
     * @return {@link net.fastix.javalib.hold.Status}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public Status getServiceStatus() throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method returns all supported shares
     *
     * @return {@link net.fastix.javalib.hold.Shares}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public Shares getSupportedShares() throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Method returns all supported antiliches
     *
     * @return {@link net.fastix.javalib.hold.Liches}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public Liches getSupportedLiches() throws FastixConnectionException, RequestUnsuccessfulException;

    /**
     * Methods return all supported sources (shares + antiliches)
     *
     * @return {@link net.fastix.javalib.hold.Sources}
     * @throws FastixConnectionException
     * @throws RequestUnsuccessfulException
     */
    public Sources getSupportedSources() throws FastixConnectionException, RequestUnsuccessfulException;

}
