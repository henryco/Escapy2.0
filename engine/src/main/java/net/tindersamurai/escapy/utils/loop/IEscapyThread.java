package net.tindersamurai.escapy.utils.loop;

public interface IEscapyThread {

	void setUpdateble(IEscapyUpdateble updateble);

	void start();

	void stop();
}