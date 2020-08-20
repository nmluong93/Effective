package exceptionhandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertStoreException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class ExceptionChecking {

	static class SynchronizedCollection<E> implements Collection<E>, Serializable {
		private static final long serialVersionUID = 3053995032091335093L;

		final Collection<E> c; // Backing Collection
		final Object mutex; // Object on which to synchronize

		SynchronizedCollection(Collection<E> c) {
			this.c = Objects.requireNonNull(c);
			mutex = this;
		}

		SynchronizedCollection(Collection<E> c, Object mutex) {
			this.c = Objects.requireNonNull(c);
			this.mutex = Objects.requireNonNull(mutex);
		}

		public int size() {
			synchronized (mutex) {
				return c.size();
			}
		}

		public boolean isEmpty() {
			synchronized (mutex) {
				return c.isEmpty();
			}
		}

		public boolean contains(Object o) {
			synchronized (mutex) {
				return c.contains(o);
			}
		}

		public Object[] toArray() {
			synchronized (mutex) {
				return c.toArray();
			}
		}

		public <T> T[] toArray(T[] a) {
			synchronized (mutex) {
				return c.toArray(a);
			}
		}

		public Iterator<E> iterator() {
			return c.iterator(); // Must be manually synched by user!
		}

		public boolean add(E e) {
			synchronized (mutex) {
				return c.add(e);
			}
		}

		public boolean remove(Object o) {
			synchronized (mutex) {
				return c.remove(o);
			}
		}

		public boolean containsAll(Collection<?> coll) {
			synchronized (mutex) {
				return c.containsAll(coll);
			}
		}

		public boolean addAll(Collection<? extends E> coll) {
			synchronized (mutex) {
				return c.addAll(coll);
			}
		}

		public boolean removeAll(Collection<?> coll) {
			synchronized (mutex) {
				return c.removeAll(coll);
			}
		}

		public boolean retainAll(Collection<?> coll) {
			synchronized (mutex) {
				return c.retainAll(coll);
			}
		}

		public void clear() {
			synchronized (mutex) {
				c.clear();
			}
		}

		public String toString() {
			synchronized (mutex) {
				return c.toString();
			}
		}

		// Override default methods in Collection
		@Override
		public void forEach(Consumer<? super E> consumer) {
			synchronized (mutex) {
				c.forEach(consumer);
			}
		}

//        @Override
//        public boolean removeIf(Predicate<? super E> filter) {
//            synchronized (mutex) {return c.removeIf(filter);}
//        }
		@Override
		public Spliterator<E> spliterator() {
			return c.spliterator(); // Must be manually synched by user!
		}

		@Override
		public Stream<E> stream() {
			return c.stream(); // Must be manually synched by user!
		}

		@Override
		public Stream<E> parallelStream() {
			return c.parallelStream(); // Must be manually synched by user!
		}

		private void writeObject(ObjectOutputStream s) throws IOException {
			synchronized (mutex) {
				s.defaultWriteObject();
			}
		}
	}

	public static void main(String[] args) {
		Collection<String> a = new SynchronizedCollection<String>(new HashSet<>(Arrays.asList("a", "b", "c", "d")));
		a.removeIf(str -> {
			return str.equals("a");
		});

		System.out.println(a);
	}

	public void check(int cipherMode, String key, File inputFile, File outputFile) throws CertStoreException {
		/*
		 * try { Key secretKey = new SecretKeySpec(key.getBytes(), "AES"); Cipher cipher
		 * = Cipher.getInstance("AES"); cipher.init(cipherMode, secretKey);
		 * 
		 * FileInputStream inputStream = new FileInputStream(inputFile); byte[]
		 * inputBytes = new byte[(int) inputFile.length()];
		 * inputStream.read(inputBytes);
		 * 
		 * byte[] outputBytes = cipher.doFinal(inputBytes);
		 * 
		 * FileOutputStream outputStream = new FileOutputStream(outputFile);
		 * outputStream.write(outputBytes);
		 * 
		 * inputStream.close(); outputStream.close();
		 * 
		 * }
		 */
		/*
		 * catch (NoSuchPaddingException ex) { System.err.println("no padding"); } catch
		 * (NoSuchAlgorithmException ex) { System.err.println("no algorithm"); } catch
		 * (InvalidKeyException ex) { System.err.println("invalid key"); } catch
		 * (BadPaddingException ex) { System.err.println("bad padding"); } catch
		 * (IllegalBlockSizeException ex) { System.err.println("illegal block"); } catch
		 * (IOException ex) { System.err.println("error reading file"); }
		 */
//		catch (NoSuchPaddingException | NoSuchAlgorithmException | Exception ex) {
//			
//		}
//catch (NoSuchAlgorithmException |NoSuchPaddingException ex) {
//			
//		}

//		catch (IOException ed) {
//			
//		}
//		catch (FileNotFoundException ex) {
//			
//		}
//		catch (NoSuchAlgorithmException ex) {
//			System.err.println("no algorithm");
//		}
//		catch (NoSuchPaddingException ex) {
//			System.err.println("no padding");
//		} 
	}
}
